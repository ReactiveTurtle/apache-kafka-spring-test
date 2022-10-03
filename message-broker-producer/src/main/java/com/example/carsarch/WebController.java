package com.example.carsarch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class WebController {
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private RabbitMQProducerService rabbitMQProducerService;

    @RequestMapping(value = "/publish_kafka", method = RequestMethod.POST)
    public String sendMessageToKafkaConsumer(@RequestParam String message){
        MessageItem messageItem = createCurrentMessage(message);
        kafkaProducerService.send(messageItem);
        return "redirect:kafka_producer.html";
    }
  
    @RequestMapping(value = "/publish_rabbit", method = RequestMethod.POST)
    public String sendMessageToRabbitMQConsumer(@RequestParam String message){
        MessageItem messageItem = createCurrentMessage(message);
        rabbitMQProducerService.send(messageItem);
        return "redirect:rabbitmq_producer.html";
    }
    
    private MessageItem createCurrentMessage(String message){
        MessageItem messageItem = new MessageItem();
        messageItem.setMessage(message);
        messageItem.setUuid(UUID.randomUUID().toString());
        messageItem.setTime(LocalDateTime.now().toString());
        return messageItem;
    }
}