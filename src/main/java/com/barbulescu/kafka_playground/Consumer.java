package com.barbulescu.kafka_playground;

import com.barbulescu.kafka_playground.config.ConsumerProperties;
import com.barbulescu.kafka_playground.config.ProducerProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Closeable;
import java.time.Duration;
import java.util.Arrays;

@Slf4j
public class Consumer implements Closeable {
    private final KafkaConsumer<String, String> kafkaConsumer;

    public Consumer(ConsumerProperties properties) {
        kafkaConsumer = new KafkaConsumer<>(properties.toProperties());
    }

    public void subscribe(String... topics) {
        kafkaConsumer.subscribe(Arrays.asList(topics));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            records.forEach(r -> {
                log.info("Key {}, Value {}", r.key(), r.value());
                log.info("Partition {}, Offset {}", r.partition(), r.offset());
            });
        }
    }


    @Override
    public void close() {
        kafkaConsumer.close();
    }
}
