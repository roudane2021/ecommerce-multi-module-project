package ma.roudane.gatewayserver.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthorizationHeaderFilter  extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {


    public AuthorizationHeaderFilter() {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->{

            if ( hasAuthorizationHeader(exchange)) {
                return chain.filter(exchange);
            }else {
                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                return exchange.getResponse().setComplete();
            }
        };
    }

    private boolean hasAuthorizationHeader(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().containsKey("Authorization");
    }

    public static class Config {
        // Vous pouvez ajouter des configurations de filtre ici si nécessaire
    }
}
