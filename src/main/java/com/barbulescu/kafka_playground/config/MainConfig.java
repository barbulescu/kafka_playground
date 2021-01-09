package com.barbulescu.kafka_playground.config;

import com.barbulescu.kafka_playground.Producer;
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
    Producer producer() {
        return new Producer(producerProperties);
    }

}
