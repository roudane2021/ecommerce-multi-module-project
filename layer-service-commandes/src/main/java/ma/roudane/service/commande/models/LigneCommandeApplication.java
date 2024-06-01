package ma.roudane.service.commande.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LigneCommandeApplication {


    private Integer quantite;

    private Integer produitId;

    @JsonBackReference
    private CommandeApplication commande;
}
