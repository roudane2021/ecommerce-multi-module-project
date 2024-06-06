package ma.roudane.commandes.web.autorisation.dto;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitCommandeRequestDto {

    private Integer produitId;
    private Integer quantite;
}
