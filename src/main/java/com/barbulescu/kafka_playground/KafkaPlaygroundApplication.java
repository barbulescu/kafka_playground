package com.barbulescu.kafka_playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class KafkaPlaygroundApplication {

    private final Producer producer;
    private final Consumer consumer;

    public KafkaPlaygroundApplication(Producer producer, Consumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @Bean
    ApplicationRunner jmsRunner() {
        return args -> {
            new Thread(() -> consumer.subscribe("first_topic")).start();

            IntStream.range(0, 20)
                    .forEach(i -> producer.send("Hello World " + i, i));

            log.info("Send executed");
            consumer.unsubscribe();
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaPlaygroundApplication.class, args);
    }
}
