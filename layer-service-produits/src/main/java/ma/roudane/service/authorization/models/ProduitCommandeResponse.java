package ma.roudane.service.authorization.models;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitCommandeResponse {

    private Integer produitId;
    private Integer accepted;


}
