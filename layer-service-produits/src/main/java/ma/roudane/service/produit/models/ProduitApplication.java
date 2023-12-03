package ma.roudane.service.produit.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitApplication {
    @Id
    @GeneratedValue
    private int id;

    private String titre;

    private String description;

    private String image;

    private Double prix;
}
