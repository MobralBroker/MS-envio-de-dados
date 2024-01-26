package com.alx.MSenviodedados.controller;

import com.alx.MSenviodedados.handler.WSHandler;
import com.alx.MSenviodedados.handler.sessionsInterface;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class KafkaConsumerService implements sessionsInterface {

    private final WSHandler wsHandler;
    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerService.class);

    @SneakyThrows
    @KafkaListener(topics = "solinfbroker.public.ordem",  groupId = "groupId")
    public void listening(ConsumerRecord<String, String> record)  throws InterruptedException {

        LOG.info("CONSUMER message from Kafka: {}", record.value());

        wsHandler.sendMessageToAll(record.value());
    }

}