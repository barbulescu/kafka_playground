package com.barbulescu.kafka_playground.config;

import com.barbulescu.kafka_playground.Consumer;
import com.barbulescu.kafka_playground.Producer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@EnableConfigurationProperties({ProducerProperties.class, ConsumerProperties.class})
public class MainConfig {

    private final ProducerProperties producerProperties;
    private final ConsumerProperties consumerProperties;

    MainConfig(ProducerProperties producerProperties, ConsumerProperties consumerProperties) {
        this.producerProperties = producerProperties;
        this.consumerProperties = consumerProperties;
    }

    @Bean
    Producer producer() {
        return new Producer(producerProperties);
    }

    @Bean
    Consumer consumer() {
        return new Consumer(consumerProperties);
    }

}
