package ma.roudane.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commande")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeEntity extends AbstractEntity {



    @Column(name = "date_commande")
    private LocalDateTime dateCommande;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private CommandeStatus status;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "commande", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommandeEntity> ligneCommandes;



}
