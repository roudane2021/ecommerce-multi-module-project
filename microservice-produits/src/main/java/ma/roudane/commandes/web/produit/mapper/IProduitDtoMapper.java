package ma.roudane.commandes.web.produit.mapper;


import ma.roudane.commandes.web.produit.dto.ProduitDto;
import ma.roudane.service.produit.models.ProduitApplication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProduitDtoMapper {

    ProduitApplication toApp(ProduitDto produitDtoproduitDto);
    ProduitDto toDto(ProduitApplication produitApplication);

    List<ProduitApplication> listToApp(List<ProduitDto> produitDtos);
    List<ProduitDto> listToDto(List<ProduitApplication> produitApplications);
}
