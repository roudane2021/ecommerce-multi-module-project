package ma.roudane.gatewayserver;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
public class GatewayServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}


	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

		return builder.routes()
				.route("commandeModule", r -> r
						.path("/commandes/**")
						//.filters(f -> f.filter(filterFactory.apply()))
						.uri("lb://microservice-commandes")
				)
				.route("produitsModule", r -> r
						.path("/produits/**")
						//.filters(f -> f.filter(filterFactory.apply()))
						.uri("lb://microservice-produits")
				)
				.build();
	}




}
