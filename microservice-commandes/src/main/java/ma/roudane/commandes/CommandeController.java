package ma.roudane.commandes;

import ma.roudane.config.PaginationParams;
import ma.roudane.commandes.dto.CommandeDto;
import ma.roudane.commandes.dto.ICriteriaDtoMapper;
import ma.roudane.commandes.mapper.CriteriaDto;
import ma.roudane.commandes.mapper.ICommandeDtoMapper;
import ma.roudane.service.commande.ICommandeService;
import ma.roudane.service.commande.models.CommandeApplication;
import ma.roudane.service.commande.models.CriteriaApp;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
        private final ICommandeService commandeService;
        private final PaginationParams paginationParams;
        private final ICommandeDtoMapper commandemapper;
        private final ICriteriaDtoMapper criteriaDtoMapper;

    public CommandeController(final ICommandeService commandeService, final ICommandeDtoMapper mapper, final PaginationParams paginationParams, final ICriteriaDtoMapper criteriaDtoMapper){
            this.commandeService = commandeService;
            this.commandemapper = mapper;
            this.paginationParams = paginationParams;
            this.criteriaDtoMapper = criteriaDtoMapper;
        }

        @PostMapping
        public ResponseEntity<CommandeDto> save(@Valid  @RequestBody final CommandeDto commande){
        CommandeApplication commandeApplication = commandemapper.toApp(commande);

           commandeApplication = commandeService.saveCommande(commandeApplication);

            return ResponseEntity.ok(toDto(commandeApplication, commandemapper::toDto));
        }

    @PostMapping(value = "/search-commande", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CommandeDto>> searchProduit(@RequestBody List<CriteriaDto> criteriaDtos, Optional<Integer> page, Optional<Integer> size){


        Pageable pageable = PageRequest.of(page.orElse(paginationParams.getPageDefault()), size.orElse(paginationParams.getSizeDefault()));
        List<CriteriaApp> criteriaApps = this.prepareCriteriaList(criteriaDtos);

        Page<CommandeDto> listPage = commandeService.searchCommandes(pageable, criteriaApps).map(commandemapper::toDto);
        return ResponseEntity.ok(listPage);
    }

        private CommandeDto toDto(final CommandeApplication commandeApplication, Function<CommandeApplication, CommandeDto> dtoFunction) {

         return dtoFunction.apply(commandeApplication);
        }

        private List<CriteriaApp> prepareCriteriaList(final List<CriteriaDto> criteriaDtos) {

        if (CollectionUtils.isEmpty(criteriaDtos)) {
            return Collections.emptyList();
        }
        return  criteriaDtos.stream()
                .map(criteriaDtoMapper::toApp)
                .collect(Collectors.toList());
        }


}
