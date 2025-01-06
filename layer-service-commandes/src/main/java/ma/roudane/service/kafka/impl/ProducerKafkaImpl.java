package ma.roudane.service.kafka.impl;

import ma.roudane.service.commande.models.PaiementApplication;
import ma.roudane.service.kafka.IProducerKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerKafkaImpl implements IProducerKafka {

    @Value("${topic.name}")
    private  String topic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void envoyerPaiement(PaiementApplication paiement) {
        kafkaTemplate.send(topic, paiement);
    }
}
