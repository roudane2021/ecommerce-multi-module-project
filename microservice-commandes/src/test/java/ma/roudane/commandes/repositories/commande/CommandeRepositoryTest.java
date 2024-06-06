package ma.roudane.commandes.repositories.commande;

import ma.roudane.entities.CommandeEntity;
import ma.roudane.repositories.ICommandeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@DataJpaTest
@ActiveProfiles("h2")
public class CommandeRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ICommandeRepository commandeRepository;

    @BeforeEach
    public void setUp() {
        // Configuration nécessaire avant chaque test
        // Par exemple, initialiser des entités de test
    }

    @AfterEach
    public void tearDown() {
        // Nettoyage après chaque test
       // entityManager.createQuery("DELETE FROM LigneCommande").executeUpdate();
      //  entityManager.createQuery("DELETE FROM Commande").executeUpdate();
    }

    @Test
    @Transactional
    public void testSaveCommande() {
        CommandeEntity commande = new CommandeEntity();
        // Configurer les propriétés de commande
        commandeRepository.save(commande);

        // Assertions pour vérifier le comportement attendu
    }

    // Autres tests
}
