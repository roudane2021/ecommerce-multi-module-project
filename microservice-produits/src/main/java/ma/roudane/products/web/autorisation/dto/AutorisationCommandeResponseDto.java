package ma.roudane.products.web.autorisation.dto;


import lombok.*;

import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorisationCommandeResponseDto {
    private Integer accepted;
    private Set<ProduitCommandeResponseDto> produitCommandeResponses;
}
