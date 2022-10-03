package com.example.carsarch;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.topic.exchangeName}")
    private String topicExchange;
    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;

    public void send(MessageItem message){
        rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
    }
}