package ma.roudane.service.commande;


import ma.roudane.entities.CommandeEntity;
import ma.roudane.entities.LigneCommandeEntity;
import ma.roudane.repositories.ICommandeRepository;
import ma.roudane.repositories.ILigneCommandeRepository;
import ma.roudane.service.commande.impl.CommandeServiceImpl;
import ma.roudane.service.commande.mapper.ICommandeApplicationMapper;
import ma.roudane.service.commande.models.CommandeApplication;
import ma.roudane.service.commande.models.LigneCommandeApplication;
import ma.roudane.service.config.ErrorKeys;
import ma.roudane.service.config.ErrorMessages;
import ma.roudane.service.exception.ExceptionApplication;
import ma.roudane.service.proxy.MicroserviceProduitsProxy;
import ma.roudane.service.proxy.dto.AutorisationCommandeProduisRequest;
import ma.roudane.service.proxy.dto.AutorisationCommandeResponse;
import ma.roudane.service.proxy.dto.ProduitCommandeRequest;
import ma.roudane.service.proxy.mapper.ProduitCommandeRequestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atMostOnce;


@ExtendWith(MockitoExtension.class)
public class CommandeServiceImplTest {

    @InjectMocks
    private CommandeServiceImpl commandeService;
    @Mock
    private ICommandeRepository commandeRepository;
    @Mock
    private ILigneCommandeRepository ligneCommandeRepository;
    @Mock
    private ICommandeApplicationMapper mapper;
    @Mock
    private MicroserviceProduitsProxy produitsProxy;
    @Mock
    private ProduitCommandeRequestMapper produitCommandeRequestMapper;

    @Test
    public void prepareLigneCommandeEntitiesEmpty() {
        // Arrange
       final CommandeEntity commandeEntity = null;

       //ACT
        final List<LigneCommandeEntity> ligneCommandeEntities = commandeService.prepareLigneCommandeEntities(commandeEntity);

        //Assert
        assertThat(ligneCommandeEntities).isNotNull();
        assertThat(ligneCommandeEntities).isEmpty();

    }

    @Test
    public void prepareLigneCommandeEntitiesNotEmpty() {
        // Arrange
        final List<LigneCommandeEntity> ligneCommandeEntitiesSend = Arrays.asList(LigneCommandeEntity.builder().produitId(1).quantite(2).build());
        final CommandeEntity commandeEntity = CommandeEntity.builder().ligneCommandes(ligneCommandeEntitiesSend).build();

        //ACT
        final List<LigneCommandeEntity> ligneCommandeEntities = commandeService.prepareLigneCommandeEntities(commandeEntity);

        //Assert
        assertThat(ligneCommandeEntities).isNotNull();
        assertThat(ligneCommandeEntities).isNotEmpty();
        assertThat(ligneCommandeEntities).hasSize(1);
        assertThat(ligneCommandeEntities).allMatch(lc -> Objects.nonNull(lc));
        assertThat(ligneCommandeEntities).extracting(lc -> lc.getCommande()).allMatch(com -> Objects.nonNull(com));

    }

    @Test
    public void prepareProduitCommandeRequestNotEmpty() {
        // Arrange
        final List<LigneCommandeApplication> ligneCommandeApplicationsSend = Arrays.asList(LigneCommandeApplication.builder().produitId(1).quantite(2).build());
        final CommandeApplication commandeApplication = CommandeApplication.builder().ligneCommandes(ligneCommandeApplicationsSend).build();
        ArgumentCaptor<LigneCommandeApplication> argumentCaptor = ArgumentCaptor.forClass(LigneCommandeApplication.class);


        given(produitCommandeRequestMapper.toRequest(any(LigneCommandeApplication.class))).willAnswer(invocationOnMock -> {
            final LigneCommandeApplication ligneCommandeApplication = invocationOnMock.getArgument(0, LigneCommandeApplication.class);
            return ProduitCommandeRequest.builder().produitId(ligneCommandeApplication.getProduitId()).quantite(ligneCommandeApplication.getQuantite()).build();
        });

        //ACT
        final Set<ProduitCommandeRequest> requests = commandeService.prepareProduitCommandeRequest(commandeApplication);


        //Assert
        then(produitCommandeRequestMapper).should(atMostOnce()).toRequest(argumentCaptor.capture());
        assertThat(requests).isNotNull();
        assertThat(requests).isNotEmpty();
        assertThat(requests).hasSize(1);
        assertThat(requests).allMatch(lc -> Objects.nonNull(lc));


    }

    @Test
    public void prepareProduitCommandeRequestEmpty() {
        // Arrange
        final List<LigneCommandeApplication> ligneCommandeApplicationsSend = null;
        final CommandeApplication commandeApplication = CommandeApplication.builder().ligneCommandes(ligneCommandeApplicationsSend).build();




        //ACT
        final Set<ProduitCommandeRequest> requests = commandeService.prepareProduitCommandeRequest(commandeApplication);


        //Assert
        assertThat(requests).isNotNull();
        assertThat(requests).isEmpty();


    }

    @Test
    public void isAutorisationAccepteeResponseEntityIsNull() {
        // Arrange
        final ResponseEntity<AutorisationCommandeResponse> response = null;

        //ACT
        final boolean autorise = commandeService.isAutorisationAcceptee(response);

        //Assert
        assertThat(autorise).isNotNull();
        assertThat(autorise).isFalse();


    }

    @Test
    public void isAutorisationAccepteeResponseEntityIsNotNullAutorise() {
        // Arrange
        final ResponseEntity<AutorisationCommandeResponse> response = ResponseEntity.ok(AutorisationCommandeResponse.builder().accepted(1).build());

        //ACT
        final boolean autorise = commandeService.isAutorisationAcceptee(response);

        //Assert
        assertThat(autorise).isNotNull();
        assertThat(autorise).isTrue();
    }

    @Test
    public void isAutorisationAccepteeNotAutorise() {
        // Arrange
        final ResponseEntity<AutorisationCommandeResponse> response = ResponseEntity.ok(AutorisationCommandeResponse.builder().accepted(0).build());

        //ACT
        final boolean autorise = commandeService.isAutorisationAcceptee(response);

        //Assert
        assertThat(autorise).isNotNull();
        assertThat(autorise).isFalse();
    }

    @Test
    public void isAutorisationAccepteeNotAutoriseBadResponse() {
        // Arrange
        final ResponseEntity<AutorisationCommandeResponse> response = ResponseEntity.badRequest().build();

        //ACT
        final boolean autorise = commandeService.isAutorisationAcceptee(response);

        //Assert
        assertThat(autorise).isNotNull();
        assertThat(autorise).isFalse();
    }

    @Test
    public void saveCommandeAutorise() {
        // Arrange
        final List<LigneCommandeApplication> ligneCommandeApplicationsSend = Arrays.asList(LigneCommandeApplication.builder().produitId(1).quantite(2).build());
        final CommandeApplication commandeApplication = CommandeApplication.builder().ligneCommandes(ligneCommandeApplicationsSend).build();
        AutorisationCommandeResponse autorisationCommandeResponse = AutorisationCommandeResponse.builder().accepted(1).build();
        ArgumentCaptor<LigneCommandeApplication> argumentCaptor = ArgumentCaptor.forClass(LigneCommandeApplication.class);

        given(produitsProxy.autorisationCommande(any(AutorisationCommandeProduisRequest.class))).willReturn(ResponseEntity.ok().body(autorisationCommandeResponse));
        given(mapper.toEntity(any(CommandeApplication.class))).willAnswer(invocationOnMock -> {
            CommandeApplication application = invocationOnMock.getArgument(0, CommandeApplication.class);

            if (Objects.isNull(application)) {
                return null;
            }

            return CommandeEntity.builder().build();
            // pour test

        });
        given(mapper.toApp(any(CommandeEntity.class))).willReturn(commandeApplication);

        given(commandeRepository.save(any(CommandeEntity.class))).willAnswer(invocationOnMock -> {

            return  invocationOnMock.getArgument(0, CommandeEntity.class);
        });

        given(produitCommandeRequestMapper.toRequest(any(LigneCommandeApplication.class))).willReturn(ProduitCommandeRequest.builder().build());

        //ACT
        final CommandeApplication reponse = commandeService.saveCommande(commandeApplication);

        //Assert
        assertThat(reponse).isNotNull();
        assertThat(reponse).extracting(CommandeApplication::getLigneCommandes).isNotNull();

    }

    @Test
    public void saveCommandeNotAutorise() {
        // Arrange
        final List<LigneCommandeApplication> ligneCommandeApplicationsSend = Arrays.asList(LigneCommandeApplication.builder().produitId(1).quantite(2).build());
        final CommandeApplication commandeApplication = CommandeApplication.builder().ligneCommandes(ligneCommandeApplicationsSend).build();
        AutorisationCommandeResponse autorisationCommandeResponse = AutorisationCommandeResponse.builder().accepted(0).build();
        ArgumentCaptor<LigneCommandeApplication> argumentCaptor = ArgumentCaptor.forClass(LigneCommandeApplication.class);

        given(produitsProxy.autorisationCommande(any(AutorisationCommandeProduisRequest.class))).willReturn(ResponseEntity.ok().body(autorisationCommandeResponse));

        given(produitCommandeRequestMapper.toRequest(any(LigneCommandeApplication.class))).willReturn(ProduitCommandeRequest.builder().build());

        //ACT


        //Assert
        assertThatThrownBy(() -> {
            commandeService.saveCommande(commandeApplication);
        }).isInstanceOf(ExceptionApplication.class).hasMessage(ErrorMessages.getMessage(ErrorKeys.ERREUR_AUTORISATION_NON_ACCEPTEE));

    }
}
