package ma.roudane.service.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaiementApplication {
    private Integer idCommande;
    private Integer montant;
    private Long numeroCarte;
}
