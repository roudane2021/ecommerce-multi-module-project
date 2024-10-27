package ma.roudane.commandes.dto;



import ma.roudane.commandes.mapper.CriteriaDto;
import ma.roudane.service.commande.models.CriteriaApp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICriteriaDtoMapper {

    CriteriaApp toApp(CriteriaDto criteriaDto);

    List<CriteriaApp> listToApp(List<CriteriaDto> criteriaDtos);

}
