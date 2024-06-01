package ma.roudane.repositories;

import ma.roudane.entities.ProduitEntity;
import ma.roudane.repositories.projections.ProduitQuantityProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProduitRepository extends JpaRepository<ProduitEntity, Integer>, JpaSpecificationExecutor<ProduitEntity> {

    @Query("SELECT p.id AS id, p.quantite AS quantite FROM ProduitEntity AS p WHERE p.id IN :listId")
    List<ProduitQuantityProjection> findProduitQuantiteByIds(@Param("listId") List<Integer> listId);

    @Modifying
    @Query("UPDATE ProduitEntity p SET p.quantite = p.quantite - :quantite WHERE p.id = :id")
    void updateQuantite(@Param("id") Integer id, @Param("quantite") Integer quantite);
}
