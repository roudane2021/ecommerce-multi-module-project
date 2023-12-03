package ma.roudane.paiement.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaiementDto {
    private int id;

    private Integer idCommande;

    private Integer montant;

    private Long numeroCarte;
}
