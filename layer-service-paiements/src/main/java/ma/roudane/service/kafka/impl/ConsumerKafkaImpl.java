package ma.roudane.service.kafka.impl;


import ma.roudane.service.kafka.IConsumerKafka;
import ma.roudane.service.kafka.models.PaiementApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerKafkaImpl implements IConsumerKafka {

    @KafkaListener(topics = "${topic.name}", groupId = "${topic.group}")
    public void listen(final PaiementApplication paiement) {

        System.out.println("Received message2: " + paiement);
    }
}
