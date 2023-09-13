package ma.roudane.service.produit.mapper;

import ma.roudane.entities.ProduitEntity;
import ma.roudane.service.produit.models.ProduitApplication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProduitAppMapper {

    ProduitEntity toEntity(ProduitApplication produitApplication);
    ProduitApplication toApplication(ProduitEntity produitEntity);

    List<ProduitEntity> listToEntity(List<ProduitApplication> produitApplication);
    List<ProduitApplication> listToApplication(List<ProduitEntity> produitEntity);
}
