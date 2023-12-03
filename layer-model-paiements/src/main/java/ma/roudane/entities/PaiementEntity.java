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
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private Integer idCommande;

    private Integer montant;

    private Long numeroCarte;
}
