package ma.roudane.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "paiement")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaiementEntity {

    @Id
    @SequenceGenerator(name = "produit_seq", sequenceName = "produit_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produit_seq")
    private int id;

    @Column(unique = true)
    private Integer idCommande;

    private Integer montant;

    private Long numeroCarte;
}
