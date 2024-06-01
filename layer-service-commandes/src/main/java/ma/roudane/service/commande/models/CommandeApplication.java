package ma.roudane.service.commande.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeApplication {




    private LocalDateTime dateCommande;


    private String firstName;


    private String lastName;


    private CommandeStatus status;


    private String email;


    private String phone;


    private String username;


    private String password;


    private List<LigneCommandeApplication> ligneCommandes;

}
