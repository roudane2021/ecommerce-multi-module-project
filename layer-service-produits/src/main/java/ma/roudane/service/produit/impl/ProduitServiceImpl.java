package ma.roudane.service.produit.impl;


import ma.roudane.entities.ProduitEntity;
import ma.roudane.repositories.IProduitRepository;
import ma.roudane.service.produit.IProduitService;
import ma.roudane.service.produit.mapper.IProduitAppMapper;
import ma.roudane.service.produit.models.ProduitApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl  implements IProduitService {

    private final IProduitRepository produitRepository;
    private final IProduitAppMapper mapper;

    public ProduitServiceImpl(IProduitRepository produitRepository, final IProduitAppMapper mapper) {
        this.produitRepository = produitRepository;
        this.mapper = mapper;
    }


    @Override
    public ProduitApplication save(ProduitApplication produit) {

        ProduitEntity entity = this.produitRepository.save(mapper.toEntity(produit));

        return mapper.toApplication(entity);
    }

    @Override
    public List<ProduitApplication> listAllProduit() {

        List<ProduitApplication> produitApplications = produitRepository.findAll().stream().map(mapper::toApplication).collect(Collectors.toList());
        return produitApplications;
    }
}
