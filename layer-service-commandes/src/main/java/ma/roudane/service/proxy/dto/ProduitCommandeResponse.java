package ma.roudane.service.proxy.dto;


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
