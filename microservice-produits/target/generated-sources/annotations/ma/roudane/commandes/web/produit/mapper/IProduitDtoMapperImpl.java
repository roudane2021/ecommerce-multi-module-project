package ma.roudane.commandes.web.produit.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ma.roudane.commandes.web.produit.dto.ProduitDto;
import ma.roudane.service.produit.models.ProduitApplication;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T16:59:52+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.19 (Oracle Corporation)"
)
@Component
public class IProduitDtoMapperImpl implements IProduitDtoMapper {

    @Override
    public ProduitApplication toApp(ProduitDto produitDto) {
        if ( produitDto == null ) {
            return null;
        }

        ProduitApplication.ProduitApplicationBuilder produitApplication = ProduitApplication.builder();

        produitApplication.id( produitDto.getId() );
        produitApplication.titre( produitDto.getTitre() );
        produitApplication.description( produitDto.getDescription() );
        produitApplication.image( produitDto.getImage() );
        produitApplication.prix( produitDto.getPrix() );

        return produitApplication.build();
    }

    @Override
    public ProduitDto toDto(ProduitApplication produitApplication) {
        if ( produitApplication == null ) {
            return null;
        }

        ProduitDto.ProduitDtoBuilder produitDto = ProduitDto.builder();

        produitDto.id( produitApplication.getId() );
        produitDto.titre( produitApplication.getTitre() );
        produitDto.description( produitApplication.getDescription() );
        produitDto.image( produitApplication.getImage() );
        produitDto.prix( produitApplication.getPrix() );

        return produitDto.build();
    }

    @Override
    public List<ProduitApplication> listToApp(List<ProduitDto> produitDtos) {
        if ( produitDtos == null ) {
            return null;
        }

        List<ProduitApplication> list = new ArrayList<ProduitApplication>( produitDtos.size() );
        for ( ProduitDto produitDto : produitDtos ) {
            list.add( toApp( produitDto ) );
        }

        return list;
    }

    @Override
    public List<ProduitDto> listToDto(List<ProduitApplication> produitApplications) {
        if ( produitApplications == null ) {
            return null;
        }

        List<ProduitDto> list = new ArrayList<ProduitDto>( produitApplications.size() );
        for ( ProduitApplication produitApplication : produitApplications ) {
            list.add( toDto( produitApplication ) );
        }

        return list;
    }
}
