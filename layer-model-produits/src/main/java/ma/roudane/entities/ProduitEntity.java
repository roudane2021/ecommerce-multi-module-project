package ma.roudane.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "produit")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitEntity {
    @Id
    @SequenceGenerator(name = "produit_seq", sequenceName = "produit_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produit_seq")
    private int id;

    private String titre;

    private String description;

    private String image;

    private Double prix;

    private Integer quantite;
}
