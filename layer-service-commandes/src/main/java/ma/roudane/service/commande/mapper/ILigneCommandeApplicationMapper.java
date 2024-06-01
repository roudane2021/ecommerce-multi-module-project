package ma.roudane.service.commande.mapper;


import ma.roudane.entities.LigneCommandeEntity;
import ma.roudane.service.commande.models.LigneCommandeApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ILigneCommandeApplicationMapper {

    @Named("noCommande")
    @Mapping(target = "commande", ignore = true)
    LigneCommandeApplication toApp(LigneCommandeEntity entity);

    LigneCommandeEntity toEntity(LigneCommandeApplication app);
}
