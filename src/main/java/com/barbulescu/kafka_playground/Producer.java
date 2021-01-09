package com.barbulescu.kafka_playground;

import com.barbulescu.kafka_playground.config.ProducerProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Closeable;

@Slf4j
public class Producer implements Closeable {
    private final KafkaProducer<String, String> kafkaProducer;

    public Producer(ProducerProperties properties) {
        kafkaProducer = new KafkaProducer<String, String>(properties.toProperties());
    }

    public void send(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", message);
        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception == null) {
                log.info("Topic {}, Partition {}, Offset {}, Timestamp {}", metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
            } else {
                log.error("Record send error", exception);
            }
        });
        kafkaProducer.flush();
    }


    @Override
    public void close() {
        kafkaProducer.close();
    }
}
