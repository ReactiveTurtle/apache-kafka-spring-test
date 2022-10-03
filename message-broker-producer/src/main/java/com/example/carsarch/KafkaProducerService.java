package com.example.carsarch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Value("${spring.kafka.producer.topic.message}")
    private String topic;
    @Autowired
    private KafkaTemplate<String, MessageItem> kafkaTemplate;

    public void send(MessageItem messageItem){
        kafkaTemplate.send(topic, messageItem);
    }
}