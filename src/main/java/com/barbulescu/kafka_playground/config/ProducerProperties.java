package com.barbulescu.kafka_playground.config;

import lombok.Value;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Properties;

@ConfigurationProperties(prefix = "producer")
@Value
@ConstructorBinding
public class ProducerProperties {
    String bootstrapServers;
    String keySerializer;
    String valueSerializer;

    public Properties toProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return properties;
    }
}
