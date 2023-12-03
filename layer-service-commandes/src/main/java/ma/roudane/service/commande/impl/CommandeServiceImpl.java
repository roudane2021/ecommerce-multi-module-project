package ma.roudane.service.commande.impl;

import ma.roudane.repositories.ICommandeRepository;
import ma.roudane.service.commande.ICommandeService;
import ma.roudane.service.commande.mapper.ICommandeApplicationMapper;
import ma.roudane.service.commande.models.CommandeApplication;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommandeServiceImpl implements ICommandeService {

    private final ICommandeRepository commandeRepository;
    private final ICommandeApplicationMapper mapper;

    public CommandeServiceImpl(final ICommandeRepository commandeRepository, final ICommandeApplicationMapper mapper) {
        this.commandeRepository = commandeRepository;
        this.mapper = mapper;
    }
    @Override
    public CommandeApplication saveCommande(CommandeApplication commande) {
        return null;
    }

    @Override
    public Optional<CommandeApplication> getCommande(Integer id) {
        return Optional.empty();
    }
}
