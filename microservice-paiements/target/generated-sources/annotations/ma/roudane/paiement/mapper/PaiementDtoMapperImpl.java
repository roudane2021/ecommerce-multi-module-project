package ma.roudane.paiement.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ma.roudane.paiement.dto.PaiementDto;
import ma.roudane.service.paiement.models.PaiementApplication;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T14:46:26+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.19 (Oracle Corporation)"
)
@Component
public class PaiementDtoMapperImpl implements PaiementDtoMapper {

    @Override
    public PaiementApplication toApp(PaiementDto dto) {
        if ( dto == null ) {
            return null;
        }

        PaiementApplication.PaiementApplicationBuilder paiementApplication = PaiementApplication.builder();

        paiementApplication.id( dto.getId() );
        paiementApplication.idCommande( dto.getIdCommande() );
        paiementApplication.montant( dto.getMontant() );
        paiementApplication.numeroCarte( dto.getNumeroCarte() );

        return paiementApplication.build();
    }

    @Override
    public PaiementDto toDto(PaiementApplication app) {
        if ( app == null ) {
            return null;
        }

        PaiementDto.PaiementDtoBuilder paiementDto = PaiementDto.builder();

        paiementDto.id( app.getId() );
        paiementDto.idCommande( app.getIdCommande() );
        paiementDto.montant( app.getMontant() );
        paiementDto.numeroCarte( app.getNumeroCarte() );

        return paiementDto.build();
    }

    @Override
    public List<PaiementApplication> listToApp(List<PaiementDto> produitDtos) {
        if ( produitDtos == null ) {
            return null;
        }

        List<PaiementApplication> list = new ArrayList<PaiementApplication>( produitDtos.size() );
        for ( PaiementDto paiementDto : produitDtos ) {
            list.add( toApp( paiementDto ) );
        }

        return list;
    }

    @Override
    public List<PaiementDto> listToDto(List<PaiementApplication> produitApplications) {
        if ( produitApplications == null ) {
            return null;
        }

        List<PaiementDto> list = new ArrayList<PaiementDto>( produitApplications.size() );
        for ( PaiementApplication paiementApplication : produitApplications ) {
            list.add( toDto( paiementApplication ) );
        }

        return list;
    }
}
