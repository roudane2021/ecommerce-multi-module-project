package ma.roudane.service.produit;

import ma.roudane.service.produit.models.CriteriaApplication;
import ma.roudane.service.produit.models.ProduitApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProduitService {

    ProduitApplication save(ProduitApplication produit);
    List<ProduitApplication> listAllProduit();

    Page<ProduitApplication> searchProduits(Pageable pageable, List<CriteriaApplication> criteriaApplications);
}
