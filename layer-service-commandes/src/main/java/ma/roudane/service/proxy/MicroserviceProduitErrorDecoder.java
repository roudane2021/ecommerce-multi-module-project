package ma.roudane.service.proxy;

import feign.Response;
import feign.codec.ErrorDecoder;
import ma.roudane.service.config.ErrorKeys;
import ma.roudane.service.config.ErrorMessages;
import ma.roudane.service.exception.ExceptionApplication;

public class MicroserviceProduitErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 404:
                return new ExceptionApplication(ErrorMessages.getMessage(ErrorKeys.ERREUR_APPLICATION_COMMUNICATION)+ response.reason());

            default:
                return new ExceptionApplication(ErrorMessages.getMessage(ErrorKeys.ERREUR_APPLICATION_COMMUNICATION));
        }

    }
}
