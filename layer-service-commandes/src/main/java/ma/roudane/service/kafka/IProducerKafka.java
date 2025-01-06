package ma.roudane.service.kafka;

import ma.roudane.service.commande.models.PaiementApplication;

public interface IProducerKafka {
    void envoyerPaiement(PaiementApplication paiement);
}
