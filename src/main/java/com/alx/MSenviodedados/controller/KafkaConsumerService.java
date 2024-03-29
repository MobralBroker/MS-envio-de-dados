package com.alx.MSenviodedados.controller;

import com.alx.MSenviodedados.handler.WSDashboardHandler;
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
    private final WSDashboardHandler wsDash;

    private final Logger Log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @SneakyThrows
    @KafkaListener(topics = "solinfbroker.public.ordem",  groupId = "grupo-envio")
    public void listening(ConsumerRecord<String, String> value)   {
        Log.info("CONSUMER message from Kafka: {}", value.value());
        wsHandler.sendMessageToAll("{\"tipo\":\"ordem\",\"dados\":" + value.value()+"}");
    }

    @SneakyThrows
    @KafkaListener(topics = "solinfbroker.public.historico_preco",  groupId = "grupo-envio")
    public void listeningHistoricoPreco(ConsumerRecord<String, String> value)   {
        Log.info("CONSUMER message from Kafka: {}", value.value());
        wsDash.sendMessageToAll("{\"tipo\":\"historico\",\"dados\": "+value.value()+"}");
    }

    @SneakyThrows
    @KafkaListener(topics = "solinfbroker.public.ativo",  groupId = "grupo-envio")
    public void listeningAtivo(ConsumerRecord<String, String> value)   {
        Log.info("CONSUMER message from Kafka: {}", value.value());
        wsDash.sendMessageToAll("{\"tipo\":\"ativo\",\"dados\":"+value.value()+"}");
    }

    @SneakyThrows
    @KafkaListener(topics = "solinfbroker.public.cliente",  groupId = "grupo-envio")
    public void listeningDash(ConsumerRecord<String, String> value)   {
        Log.info("CONSUMER message from Kafka: {}", value.value());
        wsDash.sendMessageToAll("{\"tipo\":\"cliente\",\"dados\":"+value.value()+"}");
    }

}