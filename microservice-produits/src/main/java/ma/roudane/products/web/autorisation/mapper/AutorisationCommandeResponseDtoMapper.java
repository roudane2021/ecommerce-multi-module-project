package ma.roudane.products.web.autorisation.mapper;


import ma.roudane.products.web.autorisation.dto.AutorisationCommandeResponseDto;
import ma.roudane.service.authorization.models.AutorisationCommandeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorisationCommandeResponseDtoMapper {

    AutorisationCommandeResponseDto toDto(AutorisationCommandeResponse autorisationCommandeResponse);

}

