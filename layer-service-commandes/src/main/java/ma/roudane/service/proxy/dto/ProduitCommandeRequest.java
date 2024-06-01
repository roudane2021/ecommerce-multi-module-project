package ma.roudane.service.proxy.dto;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitCommandeRequest {

    private Integer produitId;
    private Integer quantite;
}
