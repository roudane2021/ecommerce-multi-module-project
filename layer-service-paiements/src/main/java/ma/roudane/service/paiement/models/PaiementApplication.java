package ma.roudane.service.paiement.models;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaiementApplication {
    private int id;

    private Integer idCommande;

    private Integer montant;

    private Long numeroCarte;
}
