package com.barbulescu.kafka_playground.config;

import lombok.Value;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@ConfigurationProperties(prefix = "consumer")
@Value
@ConstructorBinding
public class ConsumerProperties {
    String bootstrapServers;
    String keyDeserializer;
    String valueDeserializer;
    String groupId;
    String autoOffsetReset;

    public Properties toProperties() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        properties.put(GROUP_ID_CONFIG, groupId);
        properties.put(AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        return properties;
    }
}
