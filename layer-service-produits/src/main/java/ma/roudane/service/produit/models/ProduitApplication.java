package ma.roudane.service.produit.models;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitApplication {


    private String titre;

    private String description;

    private String image;

    private Double prix;

    private Integer quantite;
}
