package ma.roudane.products.web.autorisation;


import ma.roudane.products.config.ressources.IAutorisationCommandeProduits;
import ma.roudane.products.web.autorisation.dto.AutorisationCommandeProduisRequestDto;
import ma.roudane.products.web.autorisation.dto.AutorisationCommandeResponseDto;
import ma.roudane.products.web.autorisation.mapper.AutorisationCommandeProduisRequestDtoMapper;
import ma.roudane.products.web.autorisation.mapper.AutorisationCommandeResponseDtoMapper;
import ma.roudane.service.authorization.IProductOrderAuthorizationService;
import ma.roudane.service.authorization.models.AutorisationCommandeProduisRequest;
import ma.roudane.service.authorization.models.AutorisationCommandeResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autorisation-produits")
public class AutorisationCommandeProduits implements IAutorisationCommandeProduits {

    private final IProductOrderAuthorizationService productOrderAuthorizationService;
    private final AutorisationCommandeProduisRequestDtoMapper autorisationCommandeRespMapper;
    private final AutorisationCommandeResponseDtoMapper autorisationCommandeResponseDtoMapper;

    public AutorisationCommandeProduits(IProductOrderAuthorizationService productOrderAuthorizationService,
                                        final AutorisationCommandeProduisRequestDtoMapper autorisationCommandeRespMapper,
                                        final AutorisationCommandeResponseDtoMapper autorisationCommandeResponseDtoMapper
                                        ) {
        this.productOrderAuthorizationService = productOrderAuthorizationService;
        this.autorisationCommandeRespMapper = autorisationCommandeRespMapper;
        this.autorisationCommandeResponseDtoMapper = autorisationCommandeResponseDtoMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<AutorisationCommandeResponseDto> autorisationCommandeProduits(@RequestBody AutorisationCommandeProduisRequestDto commandeProduisRequest)  {

        AutorisationCommandeProduisRequest autorisationCommandeProduisRequest = this.autorisationCommandeRespMapper.toApp(commandeProduisRequest);
        AutorisationCommandeResponse autorisationCommandeResponse = this.productOrderAuthorizationService.authorizationOrder(autorisationCommandeProduisRequest);
        AutorisationCommandeResponseDto autorisationCommandeResponseDto = this.autorisationCommandeResponseDtoMapper.toDto(autorisationCommandeResponse);
        return ResponseEntity.ok(autorisationCommandeResponseDto);
    }
}
