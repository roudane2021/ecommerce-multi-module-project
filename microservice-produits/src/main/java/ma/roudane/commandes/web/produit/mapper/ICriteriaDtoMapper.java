package ma.roudane.produits.web.produit.mapper;


import ma.roudane.produits.web.produit.dto.CriteriaDto;
import ma.roudane.service.produit.models.CriteriaApplication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICriteriaDtoMapper {

    CriteriaApplication toApp(CriteriaDto criteriaDto);

    List<CriteriaApplication> listToApp(List<CriteriaDto> criteriaDtos);

}
