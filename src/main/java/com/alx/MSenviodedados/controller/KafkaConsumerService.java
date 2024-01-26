package com.alx.MSenviodedados.controller;

import com.alx.MSenviodedados.handler.WSHandler;
import com.alx.MSenviodedados.handler.teste;
import com.alx.MSenviodedados.service.WebSocketMessageBroker;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class KafkaConsumerService implements teste {

    private final WSHandler wsHandler;



    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerService.class);

    @SneakyThrows
    @KafkaListener(topics = "solinfbroker.public.ordem",  groupId = "groupId")
    public void listening(ConsumerRecord<String, String> record)  throws InterruptedException {

        LOG.info("CONSUMER message from Kafka: {}", record.value());

        wsHandler.sendMessageToAll(record.value());
        /* Business rule code with message */
    }







//    //private final WebSocketMessageBroker messagingTemplate; WebSocketMessageBroker messagingTemplate,
//
//    private final WebSocketSession session;
//
//    public KafkaConsumerService(WebSocketSession session) {
//        //this.messagingTemplate = messagingTemplate;
//        this.session = session;
//    }
//
//    @KafkaListener(topics = "solinfbroker.public.ordem", groupId = "groupId")
//    public void listen(String message) throws IOException {
//        //session.sendMessage(new TextMessage(message + UUID.randomUUID()));
//
//        //messagingTemplate.sendMessage("/topic/websocketTopic", message);
//    }
}