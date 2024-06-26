package ma.roudane.commandes.mapper;


import ma.roudane.commandes.dto.LigneCommandeDto;
import ma.roudane.service.commande.models.LigneCommandeApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ILigneCommandeMapper {

    @Named("noCommande")
    @Mapping(target = "commande", ignore = true)
    LigneCommandeApplication toApp(LigneCommandeDto dto);

    LigneCommandeDto toDto(LigneCommandeApplication app);
}
