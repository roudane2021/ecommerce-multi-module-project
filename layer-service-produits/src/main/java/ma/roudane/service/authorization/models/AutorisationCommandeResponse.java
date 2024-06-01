package ma.roudane.service.authorization.models;


import lombok.*;

import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorisationCommandeResponse {
    private Integer accepted;
    private Set<ProduitCommandeResponse> produitCommandeResponses;
}
