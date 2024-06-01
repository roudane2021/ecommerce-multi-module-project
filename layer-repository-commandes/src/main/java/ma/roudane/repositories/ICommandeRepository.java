package ma.roudane.repositories;

import ma.roudane.entities.CommandeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommandeRepository extends IAbstractRepository<CommandeEntity, Integer> {
}
