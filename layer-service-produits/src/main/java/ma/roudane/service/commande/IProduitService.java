package ma.roudane.service.produit;

import ma.roudane.service.produit.models.ProduitApplication;

import java.util.List;

public interface IProduitService {

    ProduitApplication save(ProduitApplication produit);
    List<ProduitApplication> listAllProduit();
}
