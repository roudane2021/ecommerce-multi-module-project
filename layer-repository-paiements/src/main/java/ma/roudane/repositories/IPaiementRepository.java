package ma.roudane.repositories;


import ma.roudane.entities.PaiementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaiementRepository extends JpaRepository<PaiementEntity, Integer> {
    PaiementEntity findByidCommande(int idCommande);
}