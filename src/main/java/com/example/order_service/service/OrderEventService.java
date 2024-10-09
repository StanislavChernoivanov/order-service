package com.example.order_service.service;

import com.example.order_service.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventService {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${app.kafka.orderEventTopic}")
    private String orderTopic;
    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, OrderEvent> factory;

    public void sendOrderEvent(OrderEvent orderEvent) {
        ProducerFactory<String, OrderEvent> producerFactory = kafkaTemplate.getProducerFactory();
//        log.info("KEY_SERIALIZER: {}\n VALUE_SERIALIZER: {}", producerFactory.getKeySerializer(),
//                producerFactory.getValueSerializer(), producerFactory.ge);
        Map<String, Object> configs = producerFactory.getConfigurationProperties();
        for (Map.Entry<String, Object> entry : configs.entrySet()) {
            System.err.println(MessageFormat.format("{0} - {1}", entry.getKey(), entry.getValue()));
        }
        kafkaTemplate.send(orderTopic, orderEvent);
        for(Map.Entry<String, Object> entry : factory.getConsumerFactory().getConfigurationProperties().entrySet()) {
            System.err.println(MessageFormat.format("{0} - {1}", entry.getKey(), entry.getValue()));
        }


    }

}
