package ma.roudane.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ligne_commande")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneCommandeEntity extends AbstractEntity {


    @Id
    @GeneratedValue
    private int id;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "produit_id")
    private Integer produitId;

    @ManyToOne
    @JoinColumn(name = "commande_id",  nullable = false)
    private CommandeEntity commande;

}
