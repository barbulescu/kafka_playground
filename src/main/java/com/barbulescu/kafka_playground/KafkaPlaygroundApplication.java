package com.barbulescu.kafka_playground;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class KafkaPlaygroundApplication {

    public KafkaPlaygroundApplication(KafkaProducer<String, String> producer) {
        this.producer = producer;
    }

    private final KafkaProducer<String, String> producer;

    @Bean
    ApplicationRunner jmsRunner() {
        return args -> {
            IntStream.range(0, 20)
                    .forEach(i -> {
                        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "Hello World " + i);
                        producer.send(record, (metadata, exception) -> {
                            if (exception == null) {
                                log.info("Topic {}, Partition {}, Offset {}, Timestamp {}", metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
                            } else {
                                log.error("Record send error", exception);
                            }
                        });
                        producer.flush();
                    });

            log.info("Send executed");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaPlaygroundApplication.class, args);
    }
}
