package ma.roudane.products.web.autorisation.mapper;


import ma.roudane.products.web.autorisation.dto.AutorisationCommandeProduisRequestDto;
import ma.roudane.service.authorization.models.AutorisationCommandeProduisRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorisationCommandeProduisRequestDtoMapper {

    AutorisationCommandeProduisRequest toApp(final AutorisationCommandeProduisRequestDto commandeProduisRequestDto);

}
