package ma.roudane.products.dto;



import ma.roudane.products.mapper.CriteriaDto;
import ma.roudane.service.commande.models.CriteriaApp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICriteriaDtoMapper {

    CriteriaApp toApp(CriteriaDto criteriaDto);

    List<CriteriaApp> listToApp(List<CriteriaDto> criteriaDtos);

}
