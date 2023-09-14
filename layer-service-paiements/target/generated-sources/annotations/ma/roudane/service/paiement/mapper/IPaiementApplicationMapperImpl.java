package ma.roudane.service.paiement.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ma.roudane.entities.PaiementEntity;
import ma.roudane.service.paiement.models.PaiementApplication;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T14:46:54+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.19 (Oracle Corporation)"
)
@Component
public class IPaiementApplicationMapperImpl implements IPaiementApplicationMapper {

    @Override
    public PaiementApplication toApp(PaiementEntity paiementEntity) {
        if ( paiementEntity == null ) {
            return null;
        }

        PaiementApplication.PaiementApplicationBuilder paiementApplication = PaiementApplication.builder();

        paiementApplication.id( paiementEntity.getId() );
        paiementApplication.idCommande( paiementEntity.getIdCommande() );
        paiementApplication.montant( paiementEntity.getMontant() );
        paiementApplication.numeroCarte( paiementEntity.getNumeroCarte() );

        return paiementApplication.build();
    }

    @Override
    public PaiementEntity toEntity(PaiementApplication paiement) {
        if ( paiement == null ) {
            return null;
        }

        PaiementEntity.PaiementEntityBuilder paiementEntity = PaiementEntity.builder();

        paiementEntity.id( paiement.getId() );
        paiementEntity.idCommande( paiement.getIdCommande() );
        paiementEntity.montant( paiement.getMontant() );
        paiementEntity.numeroCarte( paiement.getNumeroCarte() );

        return paiementEntity.build();
    }

    @Override
    public List<PaiementApplication> toAppList(List<PaiementEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<PaiementApplication> list1 = new ArrayList<PaiementApplication>( list.size() );
        for ( PaiementEntity paiementEntity : list ) {
            list1.add( toApp( paiementEntity ) );
        }

        return list1;
    }

    @Override
    public List<PaiementEntity> toEntityList(List<PaiementApplication> list) {
        if ( list == null ) {
            return null;
        }

        List<PaiementEntity> list1 = new ArrayList<PaiementEntity>( list.size() );
        for ( PaiementApplication paiementApplication : list ) {
            list1.add( toEntity( paiementApplication ) );
        }

        return list1;
    }
}
