package ma.roudane.commandes.config.ressources;

import io.swagger.annotations.*;
import ma.roudane.commandes.web.produit.dto.CriteriaDto;
import ma.roudane.commandes.web.produit.dto.ProduitDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@Api(tags = "API Controller")
public interface IProduitController {

    @ApiOperation("Créer un nouveau produit")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Opération réussie", response = ProduitDto.class),
            @ApiResponse(code = 201, message = "Produit créé avec succès", response = ProduitDto.class),
            @ApiResponse(code = 400, message = "Requête incorrecte"), // Personnalisation du code 400
            @ApiResponse(code = 401, message = "Non autorisé"),
            @ApiResponse(code = 403, message = "Accès interdit"),
            @ApiResponse(code = 404, message = "Ressource non trouvée"), // Personnalisation du code 404
            @ApiResponse(code = 500, message = "Erreur interne du serveur") // Personnalisation du code 500
    })
    ResponseEntity<ProduitDto> save(
            @ApiParam(value = "Objet ProduitDto contenant les détails du produit", required = true)
            ProduitDto produit);

    @ApiOperation("List produits")
    ResponseEntity<Page<ProduitDto>> searchProduit(@ApiParam(value = "", required = true) List<CriteriaDto> criteriaDtos,
            @ApiParam(value = "numéro de page souhaiter", required = false) Optional<Integer> page,
            @ApiParam(value = "Nombre maximum d'éléments dans chaque page", required = false)  Optional<Integer> size);
}

