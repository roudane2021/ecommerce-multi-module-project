package ma.roudane.commandes.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ma.roudane.commandes.dto.CommandeDto;
import ma.roudane.service.commande.models.CommandeApplication;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T17:00:22+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.19 (Oracle Corporation)"
)
@Component
public class ICommandeDtoMapperImpl implements ICommandeDtoMapper {

    @Override
    public CommandeApplication toApp(CommandeDto dto) {
        if ( dto == null ) {
            return null;
        }

        CommandeApplication.CommandeApplicationBuilder commandeApplication = CommandeApplication.builder();

        commandeApplication.id( dto.getId() );
        commandeApplication.productId( dto.getProductId() );
        commandeApplication.dateCommande( dto.getDateCommande() );
        commandeApplication.quantite( dto.getQuantite() );
        commandeApplication.commandePayee( dto.getCommandePayee() );

        return commandeApplication.build();
    }

    @Override
    public CommandeDto toDto(CommandeApplication app) {
        if ( app == null ) {
            return null;
        }

        CommandeDto.CommandeDtoBuilder commandeDto = CommandeDto.builder();

        commandeDto.id( app.getId() );
        commandeDto.productId( app.getProductId() );
        commandeDto.dateCommande( app.getDateCommande() );
        commandeDto.quantite( app.getQuantite() );
        commandeDto.commandePayee( app.getCommandePayee() );

        return commandeDto.build();
    }

    @Override
    public List<CommandeApplication> listToApp(List<CommandeDto> produitDtos) {
        if ( produitDtos == null ) {
            return null;
        }

        List<CommandeApplication> list = new ArrayList<CommandeApplication>( produitDtos.size() );
        for ( CommandeDto commandeDto : produitDtos ) {
            list.add( toApp( commandeDto ) );
        }

        return list;
    }

    @Override
    public List<CommandeDto> listToDto(List<CommandeApplication> produitApplications) {
        if ( produitApplications == null ) {
            return null;
        }

        List<CommandeDto> list = new ArrayList<CommandeDto>( produitApplications.size() );
        for ( CommandeApplication commandeApplication : produitApplications ) {
            list.add( toDto( commandeApplication ) );
        }

        return list;
    }
}
