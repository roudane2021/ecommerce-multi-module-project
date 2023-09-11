package ma.roudane.produits.web.produit.mapper;


import ma.roudane.produits.web.produit.dto.ProduitDto;
import ma.roudane.service.produit.models.ProduitApplication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProduitDtoMapper {

    ProduitApplication toApp(ProduitDto produitDto);
    ProduitDto toDto(ProduitApplication produitApplication);

    List<ProduitApplication> listToApp(List<ProduitDto> produitDtos);
    List<ProduitDto> listToDto(List<ProduitApplication> produitApplications);
}
