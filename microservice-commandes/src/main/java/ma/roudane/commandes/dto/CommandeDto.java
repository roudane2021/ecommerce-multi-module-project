package ma.roudane.produits.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeDto {
    private int id;

    private Integer productId;

    private LocalDateTime dateCommande;

    private Integer quantite;

    private Boolean commandePayee;
}
