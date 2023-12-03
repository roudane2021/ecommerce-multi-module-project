package ma.roudane.service.paiement.mapper;


import ma.roudane.entities.PaiementEntity;
import ma.roudane.service.paiement.models.PaiementApplication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPaiementApplicationMapper {

    PaiementApplication toApp(PaiementEntity paiementEntity);

    PaiementEntity toEntity(PaiementApplication paiement);



    //List
    List<PaiementApplication> toAppList(List<PaiementEntity> list);

    List<PaiementEntity> toEntityList(List<PaiementApplication> list);


}
