package ma.roudane.paiement;

import ma.roudane.paiement.dto.PaiementDto;
import ma.roudane.paiement.mapper.PaiementDtoMapper;
import ma.roudane.service.paiement.IPaiementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaiementController {
    private final IPaiementService paiementService;
    private final PaiementDtoMapper paiementApplicationMapper;

    public PaiementController(IPaiementService paiementService, PaiementDtoMapper paiementApplicationMapper) {
        this.paiementService = paiementService;
        this.paiementApplicationMapper = paiementApplicationMapper;
    }

    @PostMapping()
    public ResponseEntity<PaiementDto> payerUneCommande(@RequestBody final PaiementDto paiement){

        //Vérifions s'il y a déjà un paiement enregistré pour cette commande
        PaiementDto paiementExistant = paiementApplicationMapper.toDto(paiementService.pagetPaiementID(paiement.getIdCommande()));
        if(paiementExistant != null) throw new RuntimeException("Cette commande est déjà payée");

        //Enregistrer le paiement
        PaiementDto nouveauPaiement = paiementApplicationMapper.toDto(paiementService.savePaiement(paiementApplicationMapper.toApp(paiement)));


        if(nouveauPaiement == null) throw new RuntimeException("Erreur, impossible d'établir le paiement, réessayez plus tard");



        //TODO Nous allons appeler le Microservice Commandes ici pour lui signifier que le paiement est accepté

        return new ResponseEntity<PaiementDto>(nouveauPaiement, HttpStatus.CREATED);

    }
}
