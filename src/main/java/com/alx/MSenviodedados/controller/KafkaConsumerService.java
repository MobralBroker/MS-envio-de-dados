package com.alx.MSenviodedados.controller;

import com.alx.MSenviodedados.service.WebSocketMessageBroker;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final WebSocketMessageBroker messagingTemplate;

    public KafkaConsumerService(WebSocketMessageBroker messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "solinfbroker.public.ordem", groupId = "groupId")
    public void listen(String message) {
        messagingTemplate.sendMessage("/topic/websocketTopic", message);
    }
}