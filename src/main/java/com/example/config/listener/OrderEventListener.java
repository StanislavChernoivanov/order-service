package com.example.config.listener;

import com.example.order_service.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
public class OrderEventListener {

    @KafkaListener(topics = "${app.kafka.orderEventTopic}"
            , groupId = "${app.kafka.kafkaEventGroupId}"
            , containerFactory = "concurrentKafkaListenerContainerFactory")
    public void listen(@Payload OrderEvent orderEvent,
                       @Header(value = KafkaHeaders.KEY, required = false) UUID key,
                       @Header(KafkaHeaders.PARTITION) Integer partition,
                       @Header(KafkaHeaders.TOPIC) String topic,
                       @Header(KafkaHeaders.TIMESTAMP) Instant timestamp) {
        log.info("Received message: {}", orderEvent);
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);
    }
}
