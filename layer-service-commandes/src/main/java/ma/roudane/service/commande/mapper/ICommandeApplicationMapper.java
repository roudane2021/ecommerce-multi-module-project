package ma.roudane.service.commande.mapper;

import ma.roudane.entities.CommandeEntity;
import ma.roudane.service.commande.models.CommandeApplication;

import java.util.List;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICommandeApplicationMapper {
    CommandeApplication CommandeEntityToApp(CommandeEntity commandeEntity);

    CommandeEntity CommandeAppToEntity(CommandeApplication commande);



    //List
    List<CommandeApplication> CommandeEntityToAppList(List<CommandeEntity> list);

    List<CommandeEntity> CommandeAppToEntity(List<CommandeApplication> commande);

}
