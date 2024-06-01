package ma.roudane.service.proxy.dto;


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
