package ma.roudane.repositories;

import ma.roudane.entities.ProduitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface IProduitRepository extends JpaRepository<ProduitEntity, Integer>, JpaSpecificationExecutor<ProduitEntity> {
}
