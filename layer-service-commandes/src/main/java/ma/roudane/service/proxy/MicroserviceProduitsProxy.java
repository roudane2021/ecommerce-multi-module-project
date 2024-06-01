package ma.roudane.service.proxy;

import ma.roudane.service.proxy.dto.AutorisationCommandeProduisRequest;
import ma.roudane.service.proxy.dto.AutorisationCommandeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "${feign.client.microservice-produits.name}", url = "${feign.client.microservice-produits.url}", configuration = MicroserviceProduitsFeignConfig.class)
public interface MicroserviceProduitsProxy {

    @PostMapping(value = "/autorisation-produitsd")
    ResponseEntity<AutorisationCommandeResponse> autorisationCommande(@RequestBody AutorisationCommandeProduisRequest requestDto);

}
