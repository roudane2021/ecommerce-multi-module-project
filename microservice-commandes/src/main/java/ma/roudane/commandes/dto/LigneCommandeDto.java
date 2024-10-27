package ma.roudane.commandes.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneCommandeDto {


    private Integer quantite;

    private Integer produitId;

    @JsonBackReference
    private CommandeDto commande;
}
