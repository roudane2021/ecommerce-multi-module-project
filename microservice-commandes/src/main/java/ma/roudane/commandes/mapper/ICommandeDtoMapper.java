package ma.roudane.commandes.mapper;


import ma.roudane.commandes.dto.CommandeDto;
import ma.roudane.service.commande.models.CommandeApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ILigneCommandeMapper.class})
public interface ICommandeDtoMapper {



    @Mapping(target = "ligneCommandes", qualifiedByName = "noCommande")
    CommandeApplication toApp(CommandeDto dto);
    CommandeDto toDto(CommandeApplication app);

    List<CommandeApplication> listToApp(List<CommandeDto> produitDtos);
    List<CommandeDto> listToDto(List<CommandeApplication> produitApplications);
}
