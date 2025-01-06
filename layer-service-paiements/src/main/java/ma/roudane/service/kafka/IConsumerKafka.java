package ma.roudane.service.kafka;


import ma.roudane.service.kafka.models.PaiementApplication;

public interface IConsumerKafka {

    void listen(final PaiementApplication paiement);
}
