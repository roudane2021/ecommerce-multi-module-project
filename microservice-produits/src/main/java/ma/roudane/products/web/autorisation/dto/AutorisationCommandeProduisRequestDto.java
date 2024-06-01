package ma.roudane.products.web.autorisation.dto;


import lombok.*;

import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorisationCommandeProduisRequestDto {

    private Set<ProduitCommandeRequestDto> produitCommandes;
}
