package ma.roudane.commandes.services.commande;

import ma.roudane.service.commande.ICommandeService;
import ma.roudane.service.commande.models.CommandeApplication;
import ma.roudane.service.commande.models.CommandeStatus;
import ma.roudane.service.config.ErrorKeys;
import ma.roudane.service.config.ErrorMessages;
import ma.roudane.service.exception.ExceptionApplication;
import ma.roudane.service.proxy.MicroserviceProduitsProxy;
import ma.roudane.service.proxy.dto.AutorisationCommandeProduisRequest;
import ma.roudane.service.proxy.dto.AutorisationCommandeResponse;
import ma.roudane.service.proxy.dto.ProduitCommandeRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.*;

@SpringBootTest()
@ActiveProfiles("h2")
//@Disabled
public class ICommandeServiceTest {

    @Autowired
    private ICommandeService commandeService;

    @MockBean
    private MicroserviceProduitsProxy produitsProxy;

    @Test
    public void saveCommandeAutorisationOkTest() throws Exception {

        // Préparation des mocks
        CommandeApplication.CommandeApplicationBuilder commandeBuilder = CommandeApplication.builder();
        commandeBuilder.status(CommandeStatus.EN_ATTENTE);
        commandeBuilder.dateCommande(LocalDateTime.now());
        AutorisationCommandeResponse autorisationResponse = new AutorisationCommandeResponse();
        autorisationResponse.setAccepted(1);

        // Configuration des mocks
        given(produitsProxy.autorisationCommande(any(AutorisationCommandeProduisRequest.class))).willReturn(ResponseEntity.ok(autorisationResponse));
        //when(produitsProxy.autorisationCommande(any(AutorisationCommandeProduisRequest.class))).thenReturn(ResponseEntity.ok(autorisationResponse));

      /*  when(produitsProxy.autorisationCommande(any(AutorisationCommandeProduisRequest.class))).then(invocation -> {
            final AutorisationCommandeProduisRequest request = invocation.getArgument(0, AutorisationCommandeProduisRequest.class);
           return autorisationResponse;
        });*/

        // Appel de la méthode saveCommande
        CommandeApplication result = commandeService.saveCommande(commandeBuilder.build());

        // Capture de l'argument passé à la méthode autorisationCommande
        ArgumentCaptor<AutorisationCommandeProduisRequest> captor = ArgumentCaptor.forClass(AutorisationCommandeProduisRequest.class);
        //verify(produitsProxy).autorisationCommande(captor.capture());
        then(produitsProxy).should().autorisationCommande(captor.capture());

        AutorisationCommandeProduisRequest capturedRequest = captor.getValue();
        Set<ProduitCommandeRequest> status = capturedRequest.getProduitCommandes();

        // Vérification
        assertNotNull(result);
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(CommandeStatus.EN_ATTENTE);
        assertThat(result.getDateCommande()).isBefore(LocalDateTime.now());
        //verify(produitsProxy, times(1)).autorisationCommande(any(AutorisationCommandeProduisRequest.class));
        then(produitsProxy).should(times(1)).autorisationCommande(any(AutorisationCommandeProduisRequest.class));
        then(produitsProxy).should(atMostOnce()).autorisationCommande(any(AutorisationCommandeProduisRequest.class));
        then(produitsProxy).should(atLeast(1)).autorisationCommande(any(AutorisationCommandeProduisRequest.class));

    }

    @Test
    public void saveCommandeAutorisationNoOkTest() throws Exception{

        // Préparation des mocks
        CommandeApplication.CommandeApplicationBuilder commandeBuilder = CommandeApplication.builder();
        commandeBuilder.status(CommandeStatus.EN_ATTENTE);
        commandeBuilder.dateCommande(LocalDateTime.now());
        AutorisationCommandeResponse autorisationResponse = new AutorisationCommandeResponse();
        autorisationResponse.setAccepted(0);

        // Configuration des mocks
        given(produitsProxy.autorisationCommande(any(AutorisationCommandeProduisRequest.class))).willReturn(ResponseEntity.ok(autorisationResponse));

        // Appel de la méthode saveCommande
        assertThatThrownBy(() -> {
            commandeService.saveCommande(commandeBuilder.build());
        }).isInstanceOf(ExceptionApplication.class).hasMessage(ErrorMessages.getMessage(ErrorKeys.ERREUR_AUTORISATION_NON_ACCEPTEE));

        then(produitsProxy).should(atLeast(1)).autorisationCommande(any(AutorisationCommandeProduisRequest.class));


    }
}
