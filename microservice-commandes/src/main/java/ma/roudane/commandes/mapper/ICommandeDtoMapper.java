package ma.roudane.produits.mapper;


import ma.roudane.produits.dto.CommandeDto;
import ma.roudane.service.commande.models.CommandeApplication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICommandeDtoMapper {

    CommandeApplication toApp(CommandeDto dto);
    CommandeDto toDto(CommandeApplication app);

    List<CommandeApplication> listToApp(List<CommandeDto> produitDtos);
    List<CommandeDto> listToDto(List<CommandeApplication> produitApplications);
}
