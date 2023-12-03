package ma.roudane.produits;

import ma.roudane.produits.dto.CommandeDto;
import ma.roudane.produits.mapper.ICommandeDtoMapper;
import ma.roudane.service.commande.ICommandeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
        private final ICommandeService commandeService;
        private final ICommandeDtoMapper mapper;

    public CommandeController(final ICommandeService commandeService, final ICommandeDtoMapper mapper){
            this.commandeService = commandeService;
            this.mapper = mapper;
        }

        @PostMapping
        public CommandeDto save(@RequestBody CommandeDto commande){
            return mapper.toDto(commandeService.saveCommande(mapper.toApp(commande)));
        }

        @GetMapping
        public CommandeDto list(){

        //return mapper.toDto(commandeService.getCommande(1).get());

            return new CommandeDto();
        }


}
