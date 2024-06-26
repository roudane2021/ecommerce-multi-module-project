package ma.roudane.commandes.web.autorisation.dto;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitCommandeResponseDto {

    private Integer produitId;
    private Integer accepted;
}
