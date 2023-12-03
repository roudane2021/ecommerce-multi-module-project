package ma.roudane.produits.web.produit;

import ma.roudane.produits.web.produit.dto.ProduitDto;
import ma.roudane.produits.web.produit.mapper.IProduitDtoMapper;
import ma.roudane.service.produit.IProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/pro")
public class AppController {




    @GetMapping
    public List<ProduitDto> getAllProduit() {

        return Arrays.asList(ProduitDto.builder().id(1).titre("Titre 1").build(), ProduitDto.builder().id(2).titre("Titre 2").build());
    }

    @GetMapping("/{id}")
    public ProduitDto getAllProduit(@PathVariable int id) {

        return ProduitDto.builder().id(id).titre("Titre "+ id).build();
    }

    @PostMapping
    public ResponseEntity<ProduitDto> saveProduit(@RequestBody ProduitDto produitDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(produitDto);
    }
}
