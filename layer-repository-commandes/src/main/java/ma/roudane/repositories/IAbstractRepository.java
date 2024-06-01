package ma.roudane.repositories;

import ma.roudane.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAbstractRepository<T extends AbstractEntity, ID>  extends JpaRepository<T, ID> , JpaSpecificationExecutor<T> {
}
