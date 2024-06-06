package ma.roudane.commandes.web.produit;

import ma.roudane.commandes.config.exception.ExceptionWeb;
import ma.roudane.commandes.config.params.PaginationParams;
import ma.roudane.commandes.config.ressources.IProduitController;
import ma.roudane.commandes.web.produit.dto.CriteriaDto;
import ma.roudane.commandes.web.produit.dto.ProduitDto;
import ma.roudane.commandes.web.produit.mapper.ICriteriaDtoMapper;
import ma.roudane.commandes.web.produit.mapper.IProduitDtoMapper;
import ma.roudane.service.produit.IProduitService;
import ma.roudane.service.produit.models.CriteriaApplication;
import ma.roudane.service.produit.models.ProduitApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produits")
public class ProduitController implements IProduitController {

    private final IProduitService produitService;
    private final IProduitDtoMapper mapperProduit;

    private final ICriteriaDtoMapper mapperCriteria;

    private final PaginationParams paginationParams;

    ProduitController(final IProduitService produitService, final IProduitDtoMapper mapper, final ICriteriaDtoMapper mapperCriteria, final PaginationParams paginationParams){
        this.produitService = produitService;
        this.mapperProduit = mapper;
        this.mapperCriteria = mapperCriteria;
        this.paginationParams = paginationParams;
    }

    @PostMapping()
    @Override
    public ResponseEntity<ProduitDto> save(@RequestBody final ProduitDto produit){
       final  ProduitApplication produitApplication = mapperProduit.toApp(produit);
       final ProduitApplication produitApplicationBD = this.produitService.save(produitApplication);
       final ProduitDto produitDtoToSend = this.mapperProduit.toDto(produitApplicationBD);
        return ResponseEntity.status(HttpStatus.CREATED).body(produitDtoToSend);
    }

    @PostMapping(value = "/searchProduit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Page<ProduitDto>> searchProduit(@RequestBody List<CriteriaDto> criteriaDtos, Optional<Integer> page, Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(paginationParams.getPageDefault()), size.orElse(paginationParams.getSizeDefault()));
        List<CriteriaApplication> criteriaApplications = mapperCriteria.listToApp(criteriaDtos);
        Page<ProduitDto> listPage = produitService.searchProduits(pageable, criteriaApplications).map(mapperProduit::toDto);
        return ResponseEntity.ok(listPage);
    }
    @GetMapping(value = "/{produitID}")
    @Override
    public ResponseEntity<ProduitDto> getProduit(@PathVariable Integer produitID) {

       ProduitDto produitDto = produitService.getProduit(produitID).map(mapperProduit::toDto).orElseThrow(() -> new ExceptionWeb("Produit Not Found !!!!"));
        return ResponseEntity.ok(produitDto);
    }


}
