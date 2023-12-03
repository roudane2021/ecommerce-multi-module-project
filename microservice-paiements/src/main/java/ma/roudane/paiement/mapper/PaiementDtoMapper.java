package ma.roudane.paiement.mapper;

import ma.roudane.entities.PaiementEntity;
import ma.roudane.paiement.dto.PaiementDto;
import ma.roudane.service.paiement.models.PaiementApplication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaiementDtoMapper {


    PaiementApplication toApp(PaiementDto dto);
    PaiementDto toDto(PaiementApplication app);

    List<PaiementApplication> listToApp(List<PaiementDto> produitDtos);
    List<PaiementDto> listToDto(List<PaiementApplication> produitApplications);
}
