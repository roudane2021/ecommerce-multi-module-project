package ma.roudane.commandes.config.ressources;

import io.swagger.annotations.*;
import ma.roudane.commandes.web.autorisation.dto.AutorisationCommandeProduisRequestDto;
import ma.roudane.commandes.web.autorisation.dto.AutorisationCommandeResponseDto;
import org.springframework.http.ResponseEntity;

@Api(tags = "API  Autorisation commande produit Controller")
public interface IAutorisationCommandeProduits {

    @ApiOperation("Autorisation commande list de produit")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Opération réussie", response = AutorisationCommandeResponseDto.class),
            @ApiResponse(code = 201, message = "Produit créé avec succès", response = AutorisationCommandeResponseDto.class),
            @ApiResponse(code = 400, message = "Requête incorrecte"), // Personnalisation du code 400
            @ApiResponse(code = 401, message = "Non autorisé"),
            @ApiResponse(code = 403, message = "Accès interdit"),
            @ApiResponse(code = 404, message = "Ressource non trouvée"), // Personnalisation du code 404
            @ApiResponse(code = 500, message = "Erreur interne du serveur") // Personnalisation du code 500
    })
    ResponseEntity<AutorisationCommandeResponseDto> autorisationCommandeProduits(
            @ApiParam(value = "Objet contient tous les produits pour vérifier l'autorisation de commande", required = true)
            AutorisationCommandeProduisRequestDto commandeProduisRequest);
}
