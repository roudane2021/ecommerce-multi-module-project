package ma.roudane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"ma.roudane.*"})
@EnableJpaRepositories("ma.roudane.repositories")
@EntityScan("ma.roudane.entities")
public class MicroserviceCommandesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCommandesApplication.class, args);
	}

}
