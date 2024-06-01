package ma.roudane.service.commande;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@ComponentScan(basePackages = {"ma.roudane.service.*"})
@EnableJpaRepositories(basePackages = "ma.roudane.repositories")
public class TestConfiguration {
}
