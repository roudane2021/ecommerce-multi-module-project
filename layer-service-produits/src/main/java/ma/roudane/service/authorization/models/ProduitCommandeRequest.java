package ma.roudane.service.authorization.models;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProduitCommandeRequest {

    private Integer produitId;
    private Integer quantite;
}
