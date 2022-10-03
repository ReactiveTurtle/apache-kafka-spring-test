package com.example.carsarch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "${spring.kafka.consumer.topic.message}")
    public void receive(MessageItem message){
        messagingTemplate.convertAndSend("/topic.socket.kafka", message);
    }
}