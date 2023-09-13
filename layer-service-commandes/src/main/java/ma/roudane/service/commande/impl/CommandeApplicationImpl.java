package ma.roudane.service.commande.impl;

import ma.roudane.service.commande.ICommandeApplication;
import ma.roudane.service.commande.models.CommandeApplication;

import java.util.Optional;

public class CommandeApplicationImpl implements ICommandeApplication {
    @Override
    public CommandeApplication saveCommande(CommandeApplication commande) {
        return null;
    }

    @Override
    public Optional<CommandeApplication> getCommande(Integer id) {
        return Optional.empty();
    }
}
