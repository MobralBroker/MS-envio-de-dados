package com.alx.MSenviodedados.handler;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class WSHandler extends TextWebSocketHandler {
    private WebSocketSession currentSession;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {

        currentSession = session;

        System.out.println("[afterConnectionEstablished] session id" + session.getId());
        session.getAcceptedProtocol();
        session.getHandshakeHeaders();
        System.out.println("[afterConnectionEstablished] session id" + session.getAcceptedProtocol());
        System.out.println("[afterConnectionEstablished] session id" + session.getHandshakeHeaders());
        session.getAttributes().put("timeout", 1000);
        new Timer().scheduleAtFixedRate(new TimerTask()  {
            @Override
            public void run() {
                try {
                    currentSession.sendMessage(new TextMessage("$ALX.CHK"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }, 1000L, 1000L);


    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("[handleTextMessage] message" + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("[afterConnectionClosed] session id" + session.getId());
    }


    public void sendMessageToClient(String message) {
        try {
            if (currentSession != null && currentSession.isOpen()) {
                currentSession.sendMessage(new TextMessage(message));
            } else {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
