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

    public KafkaPlaygroundApplication(Producer producer) {
        this.producer = producer;
    }

    @Bean
    ApplicationRunner jmsRunner() {
        return args -> {
            IntStream.range(0, 20)
                    .forEach(i -> producer.send("Hello World " + i, i));

            log.info("Send executed");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaPlaygroundApplication.class, args);
    }
}
