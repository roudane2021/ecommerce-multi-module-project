package ma.roudane.service.kafka.config;

import ma.roudane.service.kafka.models.PaiementApplication;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerKafkaConfig {


    @Bean
    public ConsumerFactory<String, PaiementApplication> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092,kafka2:9093");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "roudane");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // Créez le JsonDeserializer pour PaiementApplication
        JsonDeserializer<PaiementApplication> deserializer = new JsonDeserializer<>(PaiementApplication.class);

        // Ajoutez des packages supplémentaires à la liste des paquets de confiance
        deserializer.addTrustedPackages("ma.roudane.service.commande.models", "ma.roudane.service.kafka.models");
        deserializer.setUseTypeHeaders(false);  // Désactive l'utilisation des headers pour les types

        // Enveloppez le JsonDeserializer avec ErrorHandlingDeserializer
        ErrorHandlingDeserializer<PaiementApplication> errorHandlingDeserializer =
                new ErrorHandlingDeserializer<>(deserializer);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), errorHandlingDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaiementApplication> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaiementApplication> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


}
