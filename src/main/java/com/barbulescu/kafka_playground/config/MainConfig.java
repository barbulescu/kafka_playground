package com.barbulescu.kafka_playground.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@EnableConfigurationProperties(ProducerProperties.class)
public class MainConfig {

    private final ProducerProperties producerProperties;

    MainConfig(ProducerProperties producerProperties) {
        this.producerProperties = producerProperties;
    }

    @Bean
    KafkaProducer<String, String> producer() {
        return new KafkaProducer<>(producerProperties.toProperties());
    }

}
