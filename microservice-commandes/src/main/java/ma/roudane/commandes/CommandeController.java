package ma.roudane.commandes;

import ma.roudane.commandes.dto.CommandeDto;
import ma.roudane.commandes.mapper.ICommandeDtoMapper;
import ma.roudane.service.commande.ICommandeService;
import org.springframework.web.bind.annotation.*;

public class CommandeController {
    @RestController
    @RequestMapping("/v1/commandes")
    public class ProduitController {

        private final ICommandeService commandeService;
        private final ICommandeDtoMapper mapper;

        ProduitController(final ICommandeService commandeService, final ICommandeDtoMapper mapper){
            this.commandeService = commandeService;
            this.mapper = mapper;
        }

        @PostMapping
        public CommandeDto save(@RequestBody CommandeDto commande){
            return mapper.toDto(commandeService.saveCommande(mapper.toApp(commande)));
        }

        @GetMapping
        public CommandeDto list(){
            return mapper.toDto(commandeService.getCommande(1).get());
        }
    }

}
