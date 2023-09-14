package ma.roudane.service.commande.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ma.roudane.entities.CommandeEntity;
import ma.roudane.service.commande.models.CommandeApplication;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T17:10:28+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.19 (Oracle Corporation)"
)
@Component
public class ICommandeApplicationMapperImpl implements ICommandeApplicationMapper {

    @Override
    public CommandeApplication CommandeEntityToApp(CommandeEntity commandeEntity) {
        if ( commandeEntity == null ) {
            return null;
        }

        CommandeApplication.CommandeApplicationBuilder commandeApplication = CommandeApplication.builder();

        commandeApplication.id( commandeEntity.getId() );
        commandeApplication.productId( commandeEntity.getProductId() );
        commandeApplication.dateCommande( commandeEntity.getDateCommande() );
        commandeApplication.quantite( commandeEntity.getQuantite() );
        commandeApplication.commandePayee( commandeEntity.getCommandePayee() );

        return commandeApplication.build();
    }

    @Override
    public CommandeEntity CommandeAppToEntity(CommandeApplication commande) {
        if ( commande == null ) {
            return null;
        }

        CommandeEntity.CommandeEntityBuilder commandeEntity = CommandeEntity.builder();

        commandeEntity.id( commande.getId() );
        commandeEntity.productId( commande.getProductId() );
        commandeEntity.dateCommande( commande.getDateCommande() );
        commandeEntity.quantite( commande.getQuantite() );
        commandeEntity.commandePayee( commande.getCommandePayee() );

        return commandeEntity.build();
    }

    @Override
    public List<CommandeApplication> CommandeEntityToAppList(List<CommandeEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CommandeApplication> list1 = new ArrayList<CommandeApplication>( list.size() );
        for ( CommandeEntity commandeEntity : list ) {
            list1.add( CommandeEntityToApp( commandeEntity ) );
        }

        return list1;
    }

    @Override
    public List<CommandeEntity> CommandeAppToEntity(List<CommandeApplication> commande) {
        if ( commande == null ) {
            return null;
        }

        List<CommandeEntity> list = new ArrayList<CommandeEntity>( commande.size() );
        for ( CommandeApplication commandeApplication : commande ) {
            list.add( CommandeAppToEntity( commandeApplication ) );
        }

        return list;
    }
}
