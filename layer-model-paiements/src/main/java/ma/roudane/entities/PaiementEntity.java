package ma.roudane.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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
