package ma.roudane.service.commande;

import ma.roudane.service.commande.models.CommandeApplication;
import ma.roudane.service.commande.models.CriteriaApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICommandeService {
    CommandeApplication saveCommande(CommandeApplication commande);
    Optional<CommandeApplication> getCommande(Integer id);

    Page<CommandeApplication> searchCommandes(Pageable pageable, List<CriteriaApp> criteria);
}
