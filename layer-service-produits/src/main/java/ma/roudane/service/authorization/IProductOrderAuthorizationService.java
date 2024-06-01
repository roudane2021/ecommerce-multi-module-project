package ma.roudane.service.authorization;

import ma.roudane.service.authorization.models.AutorisationCommandeProduisRequest;
import ma.roudane.service.authorization.models.AutorisationCommandeResponse;

public interface IProductOrderAuthorizationService {

    AutorisationCommandeResponse authorizationOrder(final AutorisationCommandeProduisRequest order);


}
