package ma.roudane.service.produit.impl;


import ma.roudane.entities.ProduitEntity;
import ma.roudane.repositories.IProduitRepository;
import ma.roudane.service.produit.IProduitService;
import ma.roudane.service.produit.mapper.IProduitAppMapper;
import ma.roudane.service.produit.models.CriteriaApplication;
import ma.roudane.service.produit.models.OperatorApplication;
import ma.roudane.service.produit.models.ProduitApplication;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl  implements IProduitService {

    private final IProduitRepository produitRepository;
    private final IProduitAppMapper mapper;

    public ProduitServiceImpl(IProduitRepository produitRepository, final IProduitAppMapper mapper) {
        this.produitRepository = produitRepository;
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public ProduitApplication save(ProduitApplication produit) {

        ProduitEntity entity = this.produitRepository.save(mapper.toEntity(produit));

        return mapper.toApplication(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitApplication> listAllProduit() {

        List<ProduitApplication> produitApplications = produitRepository.findAll().stream().map(mapper::toApplication).collect(Collectors.toList());
        return produitApplications;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProduitApplication> searchProduits(Pageable pageable, List<CriteriaApplication> criteriaApplications) {
        Specification<ProduitEntity> specification = constructCriteria(criteriaApplications);
        Page<ProduitApplication> produitApplications = produitRepository.findAll( specification, pageable).map(mapper::toApplication);
        return produitApplications;
    }

    private Specification<ProduitEntity> constructCriteria(final List<CriteriaApplication> criteriaApplications) {
        List<CriteriaApplication> criteriaApplicationsFilter = this.filterCriterias(criteriaApplications);
      return (root, query, criterBuilder) -> {
          Predicate predicate = criterBuilder.conjunction();
              for (CriteriaApplication criteriaApplication : criteriaApplicationsFilter ) {
                  Predicate criteriaPredicate = createPredicate(criteriaApplication, root, criterBuilder);
                  predicate = criterBuilder.and(predicate, criteriaPredicate);
              }
          return  predicate;
        };

    }

    private Predicate createPredicate(CriteriaApplication criteriaApplication, Root<ProduitEntity> root, CriteriaBuilder criteriaBuilder) {
        OperatorApplication operator = criteriaApplication.getOperator();
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


    private static  Object transferType(Root<?> root, CriteriaApplication element) {
        Class<?> cl = root.get(element.getName()).getJavaType();
        if (Integer.class.isAssignableFrom(cl)) {
            return Integer.valueOf(element.getValue().toString());
        } else if (Double.class.isAssignableFrom(cl)) {
            return Double.valueOf(element.getValue().toString());
        } else {
            return element.getValue();
        }

    }


    private static  List<Field> getEntityFields(Class<?> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .collect(Collectors.toList());
    }

    private static boolean isFieldExists(String fieldName, List<Field> fields) {
        return fields.stream()
                .anyMatch(field -> StringUtils.equals(fieldName, field.getName()));
    }

    private Expression<?> getFieldExpression(String field, Root<ProduitEntity> root) {
        return root.get(field);
    }

    private List<CriteriaApplication> filterCriterias(List<CriteriaApplication> criters) {
        if (CollectionUtils.isEmpty(criters)) {
           return new ArrayList<>();
        }
        return criters.stream().filter(
                criter -> Objects.nonNull(criter) && StringUtils.isNotBlank(criter.getName()) && Objects.nonNull(criter.getValue())
                        && isFieldExists(criter.getName(), getEntityFields(ProduitEntity.class))
        ).collect(Collectors.toList());
    }
}
