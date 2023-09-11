package ma.roudane.produits.web.produit;

import ma.roudane.entities.ProduitEntity;
import ma.roudane.produits.web.produit.dto.ProduitDto;
import ma.roudane.produits.web.produit.mapper.IProduitDtoMapper;
import ma.roudane.service.produit.IProduitService;
import ma.roudane.service.produit.mapper.IProduitAppMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/produits")
public class ProduitController {

    private final IProduitService produitService;
    private final IProduitDtoMapper mapper;

    ProduitController(final IProduitService produitService, final IProduitDtoMapper mapper){
        this.produitService = produitService;
        this.mapper = mapper;
    }

    @PostMapping
    public ProduitDto save(@RequestBody ProduitDto produit){
        return mapper.toDto(produitService.save(mapper.toApp(produit)));
    }

    @GetMapping
    public List<ProduitDto> list(){
        return mapper.listToDto(produitService.listAllProduit());
    }
}
