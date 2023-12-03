package ma.roudane.produits.web.produit;

import ma.roudane.produits.config.ressources.IProduitController;
import ma.roudane.produits.web.produit.dto.CriteriaDto;
import ma.roudane.produits.web.produit.dto.ProduitDto;
import ma.roudane.produits.web.produit.mapper.ICriteriaDtoMapper;
import ma.roudane.produits.web.produit.mapper.IProduitDtoMapper;
import ma.roudane.service.produit.IProduitService;
import ma.roudane.service.produit.models.CriteriaApplication;
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

    ProduitController(final IProduitService produitService, final IProduitDtoMapper mapper, final ICriteriaDtoMapper mapperCriteria){
        this.produitService = produitService;
        this.mapperProduit = mapper;
        this.mapperCriteria = mapperCriteria;
    }

    @PostMapping(value = "saveProduit")
    @Override
    public ResponseEntity<ProduitDto> save(@RequestBody ProduitDto produit){
        ProduitDto produitDto = mapperProduit.toDto(produitService.save(mapperProduit.toApp(produit)));
        return ResponseEntity.status(HttpStatus.CREATED).body(produit);
    }

    @PostMapping(value = "/searchProduit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Page<ProduitDto>> searchProduit(@RequestBody List<CriteriaDto> criteriaDtos, Optional<Integer> page, Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(5));
        List<CriteriaApplication> criteriaApplications = mapperCriteria.listToApp(criteriaDtos);
        Page<ProduitDto> listPage = produitService.searchProduits(pageable, criteriaApplications).map(mapperProduit::toDto);
        return ResponseEntity.ok(listPage);
    }
}
