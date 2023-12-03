package ma.roudane.produits.web.produit.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;



@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProduitDto{

    @ApiModelProperty(example = "1")
    private int id;

    @ApiModelProperty(example = "Titre")
    private String titre;

    @ApiModelProperty(example = "Description")
    private String description;
    @ApiModelProperty(example = "Image")
    private String image;
    @ApiModelProperty(example = "Prix")
    private Double prix;
}
