package ma.roudane.service.commande.mapper;

import ma.roudane.entities.CommandeEntity;
import ma.roudane.service.commande.models.CommandeApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ILigneCommandeApplicationMapper.class})
public interface ICommandeApplicationMapper {

    @Mapping(target = "ligneCommandes" , qualifiedByName = "noCommande")
    CommandeApplication toApp(CommandeEntity commandeEntity);


    CommandeEntity toEntity(CommandeApplication commande);



    //List
    List<CommandeApplication> toApp(List<CommandeEntity> list);

    List<CommandeEntity> CommandeAppToEntity(List<CommandeApplication> commande);

}
