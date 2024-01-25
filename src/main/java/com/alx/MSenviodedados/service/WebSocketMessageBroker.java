package com.alx.MSenviodedados.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketMessageBroker {

    private final SimpMessagingTemplate template;

    public WebSocketMessageBroker(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendMessage(String topic, String message) {
        template.convertAndSend(topic, message);
    }
}