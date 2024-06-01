package ma.roudane.service.authorization.models;


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
