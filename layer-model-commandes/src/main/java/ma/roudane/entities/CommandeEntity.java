package ma.roudane.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeEntity {

    @Id
    @GeneratedValue
    private int id;

    private Integer productId;

    private LocalDateTime dateCommande;

    private Integer quantite;

    private Boolean commandePayee;
}
