package com.example.carsarch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "${spring.rabbitmq.queueName}")
    public void receive(MessageItem message){
        messagingTemplate.convertAndSend("/topic.socket.rabbit", message);
    }
}