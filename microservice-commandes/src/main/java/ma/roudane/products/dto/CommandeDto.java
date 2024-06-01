package ma.roudane.products.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeDto {


    private LocalDateTime dateCommande;


    private String firstName;

    @NotNull(message = "{NotNull.commandeDto.lastName}")
    @NotBlank(message = "{NotBlank.commandeDto.lastName}")
    private String lastName;


    private CommandeStatus status;


    private String email;


    private String phone;


    private String username;


    private String password;

    @JsonManagedReference
    private List<LigneCommandeDto> ligneCommandes;
}
