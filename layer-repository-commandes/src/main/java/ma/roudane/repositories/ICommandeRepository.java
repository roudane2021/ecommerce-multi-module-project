package ma.roudane.repositories;

import ma.roudane.entities.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommandeRepository extends JpaRepository<CommandeEntity, Integer> {
}
