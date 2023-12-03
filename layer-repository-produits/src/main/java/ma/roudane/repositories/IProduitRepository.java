package ma.roudane.repositories;

import ma.roudane.entities.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IProduitRepository extends JpaRepository<ProduitEntity, Integer>, JpaSpecificationExecutor<ProduitEntity> {
}
