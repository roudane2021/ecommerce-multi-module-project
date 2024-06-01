package ma.roudane.service.proxy.mapper;

import ma.roudane.service.commande.models.LigneCommandeApplication;
import ma.roudane.service.proxy.dto.ProduitCommandeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProduitCommandeRequestMapper {

    ProduitCommandeRequest toRequest(LigneCommandeApplication ligneCommandeApplication);

}
