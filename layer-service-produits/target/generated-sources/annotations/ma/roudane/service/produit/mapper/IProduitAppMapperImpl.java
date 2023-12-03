package ma.roudane.service.produit.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ma.roudane.entities.ProduitEntity;
import ma.roudane.service.produit.models.ProduitApplication;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T17:10:14+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.19 (Oracle Corporation)"
)
@Component
public class IProduitAppMapperImpl implements IProduitAppMapper {

    @Override
    public ProduitEntity toEntity(ProduitApplication produitApplication) {
        if ( produitApplication == null ) {
            return null;
        }

        ProduitEntity.ProduitEntityBuilder produitEntity = ProduitEntity.builder();

        produitEntity.id( produitApplication.getId() );
        produitEntity.titre( produitApplication.getTitre() );
        produitEntity.description( produitApplication.getDescription() );
        produitEntity.image( produitApplication.getImage() );
        produitEntity.prix( produitApplication.getPrix() );

        return produitEntity.build();
    }

    @Override
    public ProduitApplication toApplication(ProduitEntity produitEntity) {
        if ( produitEntity == null ) {
            return null;
        }

        ProduitApplication.ProduitApplicationBuilder produitApplication = ProduitApplication.builder();

        produitApplication.id( produitEntity.getId() );
        produitApplication.titre( produitEntity.getTitre() );
        produitApplication.description( produitEntity.getDescription() );
        produitApplication.image( produitEntity.getImage() );
        produitApplication.prix( produitEntity.getPrix() );

        return produitApplication.build();
    }

    @Override
    public List<ProduitEntity> listToEntity(List<ProduitApplication> produitApplication) {
        if ( produitApplication == null ) {
            return null;
        }

        List<ProduitEntity> list = new ArrayList<ProduitEntity>( produitApplication.size() );
        for ( ProduitApplication produitApplication1 : produitApplication ) {
            list.add( toEntity( produitApplication1 ) );
        }

        return list;
    }

    @Override
    public List<ProduitApplication> listToApplication(List<ProduitEntity> produitEntity) {
        if ( produitEntity == null ) {
            return null;
        }

        List<ProduitApplication> list = new ArrayList<ProduitApplication>( produitEntity.size() );
        for ( ProduitEntity produitEntity1 : produitEntity ) {
            list.add( toApplication( produitEntity1 ) );
        }

        return list;
    }
}
