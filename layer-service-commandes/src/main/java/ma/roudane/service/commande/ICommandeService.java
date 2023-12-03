package ma.roudane.service.commande;

import ma.roudane.service.commande.models.CommandeApplication;

import java.util.Optional;

public interface ICommandeService {
    CommandeApplication saveCommande(CommandeApplication commande);
    Optional<CommandeApplication> getCommande(Integer id);
}
