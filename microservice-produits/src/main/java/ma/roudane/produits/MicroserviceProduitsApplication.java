package ma.roudane.produits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"ma.roudane.*"})
@EnableJpaRepositories("ma.roudane.repositories")
@EntityScan("ma.roudane.entities")
public class MicroserviceProduitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProduitsApplication.class, args);
	}



}
