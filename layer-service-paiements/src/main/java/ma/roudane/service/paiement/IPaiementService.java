package ma.roudane.service.paiement;

import ma.roudane.service.paiement.models.PaiementApplication;

public interface IPaiementService {
    PaiementApplication pagetPaiementID(Integer id);
    PaiementApplication savePaiement(PaiementApplication paiement);
}
