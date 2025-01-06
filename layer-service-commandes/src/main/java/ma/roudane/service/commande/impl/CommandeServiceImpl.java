package ma.roudane.service.commande.impl;

import ma.roudane.entities.CommandeEntity;
import ma.roudane.entities.CommandeStatus;
import ma.roudane.entities.LigneCommandeEntity;
import ma.roudane.repositories.ICommandeRepository;
import ma.roudane.repositories.ILigneCommandeRepository;
import ma.roudane.service.commande.ICommandeService;
import ma.roudane.service.commande.mapper.ICommandeApplicationMapper;
import ma.roudane.service.commande.models.CommandeApplication;
import ma.roudane.service.commande.models.CriteriaApp;
import ma.roudane.service.commande.models.OperatorApp;
import ma.roudane.service.commande.models.PaiementApplication;
import ma.roudane.service.config.ErrorKeys;
import ma.roudane.service.config.ErrorMessages;
import ma.roudane.service.exception.ExceptionApplication;
import ma.roudane.service.kafka.IProducerKafka;
import ma.roudane.service.proxy.MicroserviceProduitsProxy;
import ma.roudane.service.proxy.dto.AutorisationCommandeProduisRequest;
import ma.roudane.service.proxy.dto.AutorisationCommandeResponse;
import ma.roudane.service.proxy.dto.ProduitCommandeRequest;
import ma.roudane.service.proxy.mapper.ProduitCommandeRequestMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommandeServiceImpl implements ICommandeService {

    private final ICommandeRepository commandeRepository;
    private final ILigneCommandeRepository ligneCommandeRepository;
    private final ICommandeApplicationMapper mapper;
    private final MicroserviceProduitsProxy produitsProxy;
    private final ProduitCommandeRequestMapper produitCommandeRequestMapper;
    private final IProducerKafka producerKafka;


    public CommandeServiceImpl(final ICommandeRepository commandeRepository, final ILigneCommandeRepository ligneCommandeRepository, final ICommandeApplicationMapper mapper,
                               final MicroserviceProduitsProxy produitsProxy, final ProduitCommandeRequestMapper produitCommandeRequestMapper,
                               final IProducerKafka producerKafka) {
        this.commandeRepository = commandeRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.mapper = mapper;
        this.produitsProxy = produitsProxy;
        this.produitCommandeRequestMapper = produitCommandeRequestMapper;
        this.producerKafka = producerKafka;
    }
    @Override
    @Transactional
    public CommandeApplication saveCommande(final CommandeApplication commande) {
        CommandeEntity commandeEntityAfterSaved = null;
        // appel ws autorisation commande
        final AutorisationCommandeProduisRequest request = this.prepareRequestAutorisationCommande(commande);

        ResponseEntity<AutorisationCommandeResponse> responseAutorisationCommande = produitsProxy.autorisationCommande(request);

            if ( isAutorisationAcceptee(responseAutorisationCommande) ) {
                // Convertir CommandeApplication en CommandeEntity
                final CommandeEntity commandeEntity = mapper.toEntity(commande);
                // Vérifier et gérer les lignes de commande
                final List<LigneCommandeEntity> ligneCommandeEntities = this.prepareLigneCommandeEntities(commandeEntity);
                // Sauvegarder la commande avec les lignes de commande
                commandeEntity.setLigneCommandes(ligneCommandeEntities);
                commandeEntity.setStatus(CommandeStatus.EN_ATTENTE);
                commandeEntityAfterSaved = commandeRepository.save(commandeEntity);
                PaiementApplication paiementApplication = PaiementApplication.builder()
                                                                             .idCommande(commandeEntity.getId())
                                                                             .montant(0)
                                                                             .numeroCarte(155l)
                                                                              .build();
                producerKafka.envoyerPaiement(paiementApplication);
            }else {
                throw  new ExceptionApplication(ErrorMessages.getMessage(ErrorKeys.ERREUR_AUTORISATION_NON_ACCEPTEE));
            }

        // Retourner l'entité Commande sous forme d'application
        return mapper.toApp(commandeEntityAfterSaved);
    }



    @Override
    public Page<CommandeApplication> searchCommandes(Pageable pageable, List<CriteriaApp> criteriaDtos) {
        Specification<CommandeEntity> specification = constructCriteria(criteriaDtos);
        return commandeRepository.findAll(specification, pageable).map(mapper::toApp);
    }

    @Override
    public Optional<CommandeApplication> getCommande(Integer id) {
        return commandeRepository.findById(id).map(mapper::toApp);
    }

    public List<LigneCommandeEntity> prepareLigneCommandeEntities( final CommandeEntity commandeEntity) {
        if (Objects.isNull(commandeEntity) || CollectionUtils.isEmpty(commandeEntity.getLigneCommandes())) {
            return Collections.emptyList();
        }

        return  commandeEntity.getLigneCommandes().stream()
                .peek(ligneCom -> ligneCom.setCommande(commandeEntity))
                .collect(Collectors.toList());
    }

    public Set<ProduitCommandeRequest> prepareProduitCommandeRequest( final CommandeApplication commandeApplication) {
        if (Objects.isNull(commandeApplication) || CollectionUtils.isEmpty(commandeApplication.getLigneCommandes())) {
            return Collections.emptySet();
        }

        return  commandeApplication.getLigneCommandes().stream()
                .map(produitCommandeRequestMapper::toRequest)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public AutorisationCommandeProduisRequest prepareRequestAutorisationCommande( final CommandeApplication commandeApplication) {

        final AutorisationCommandeProduisRequest.AutorisationCommandeProduisRequestBuilder builderRequest = AutorisationCommandeProduisRequest.builder();

        builderRequest.produitCommandes(prepareProduitCommandeRequest(commandeApplication));

        return  builderRequest.build();
    }

    public Specification<CommandeEntity> constructCriteria(final List<CriteriaApp> criteriaApplications) {
        List<CriteriaApp> criteriaApplicationsFilter = this.filterCriterias(criteriaApplications);
        return (root, query, criterBuilder) -> {
            Predicate predicate = criterBuilder.conjunction();
            for (CriteriaApp criteriaApplication : criteriaApplicationsFilter ) {
                Predicate criteriaPredicate = createPredicate(criteriaApplication, root, criterBuilder);
                predicate = criterBuilder.and(predicate, criteriaPredicate);

            }
            return  predicate;
        };

    }

    public Predicate createPredicate(CriteriaApp criteriaApplication, Root<CommandeEntity> root, CriteriaBuilder criteriaBuilder) {
        OperatorApp operator = criteriaApplication.getOperator();
        Expression<?> fieldExpression = getFieldExpression(criteriaApplication.getName(), root);
        Object value = transferType(root, criteriaApplication);
        switch (criteriaApplication.getOperator()) {
            case EQUALS:
                return  criteriaBuilder.equal(fieldExpression, value);
            case NOT_EQUALS:
                return  criteriaBuilder.notEqual(fieldExpression, value);
            case LESS_THAN:
                return  criteriaBuilder.lt((Expression<? extends Number>)  fieldExpression, (Number) value);
            case LESS_OR_EQUAL:
                return  criteriaBuilder.le((Expression<? extends Number>)  fieldExpression, (Number) value);
            case GREATER_THAN:
                return  criteriaBuilder.gt((Expression<? extends Number>)  fieldExpression, (Number) value);
            case GREATER_OR_EQUAL:
                return  criteriaBuilder.ge((Expression<? extends Number>)  fieldExpression, (Number) value);
            case IN:
                return  fieldExpression.in(criteriaApplication.getListValue());
            case NOT_IN:
                return criteriaBuilder.not(fieldExpression.in(value));
            case LIKE:
                return  criteriaBuilder.like((Expression<String>) fieldExpression, "%"+value+"%");
            default:
                throw new UnsupportedOperationException("Opérateur non pris en charge: " + operator);
        }
    }


    public static  Object transferType(Root<?> root, CriteriaApp element) {
        Class<?> cl = root.get(element.getName()).getJavaType();
        if (Integer.class.isAssignableFrom(cl)) {
            return Integer.valueOf(element.getValue().toString());
        } else if (Double.class.isAssignableFrom(cl)) {
            return Double.valueOf(element.getValue().toString());
        } else {
            return element.getValue();
        }

    }


    public static  List<Field> getEntityFields(Class<?> entityClass) {
        return Stream.of(entityClass.getDeclaredFields())
                .collect(Collectors.toList());
    }

    public static boolean isFieldExists(String fieldName, List<Field> fields) {
        return fields.stream()
                .anyMatch(field -> StringUtils.equals(fieldName, field.getName()));
    }

    public Expression<?> getFieldExpression(String field, Root<CommandeEntity> root) {
        return root.get(field);
    }

    public List<CriteriaApp> filterCriterias(List<CriteriaApp> criters) {
        if (CollectionUtils.isEmpty(criters)) {
            return Collections.emptyList();
        }
        return criters.stream().filter(
                criter -> Objects.nonNull(criter) && StringUtils.isNotBlank(criter.getName()) && Objects.nonNull(criter.getValue())
                        && isFieldExists(criter.getName(), getEntityFields(CommandeEntity.class))
        ).collect(Collectors.toList());
    }

    public boolean isAutorisationAcceptee(final ResponseEntity<AutorisationCommandeResponse>  response ) {

        return  Optional.ofNullable(response)
                .filter(res -> HttpStatus.OK.equals(response.getStatusCode()))
                .map(ResponseEntity::getBody)
                .map(AutorisationCommandeResponse::getAccepted)
                .orElse(0) == 1 ;
    }


}
