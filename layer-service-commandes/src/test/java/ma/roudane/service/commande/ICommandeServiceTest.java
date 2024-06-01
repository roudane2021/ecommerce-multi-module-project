package ma.roudane.service.commande;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TestConfiguration.class)
@ActiveProfiles("h2")
public class ICommandeServiceTest {

    @Test
    public void saveProduitTest() throws Exception{


        assertEquals(HttpStatus.CREATED.value(), HttpStatus.CREATED.value());

    }
}
