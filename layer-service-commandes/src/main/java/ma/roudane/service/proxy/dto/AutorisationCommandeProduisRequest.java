package ma.roudane.service.proxy.dto;


import lombok.*;

import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorisationCommandeProduisRequest {

    private Set<ProduitCommandeRequest> produitCommandes;
}
