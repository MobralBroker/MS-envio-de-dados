package com.alx.MSenviodedados.handler;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.io.IOException;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WSHandler extends TextWebSocketHandler {
//    private WebSocketSession currentSession;
    private Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {

//        currentSession = session;
        sessions.add(session);

        System.out.println("[afterConnectionEstablished] session id" + session.getId());
        session.getAcceptedProtocol();
        session.getHandshakeHeaders();
        System.out.println("[afterConnectionEstablished] session id" + session.getAcceptedProtocol());
        System.out.println("[afterConnectionEstablished] session id" + session.getHandshakeHeaders());
        session.getAttributes().put("timeout", 1000);



    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("[handleTextMessage] message" + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("[afterConnectionClosed] session id" + session.getId());
        sessions.remove(session);
    }




//    public void sendMessageToClient(String message) {
//        try {
//            if (currentSession != null && currentSession.isOpen()) {
//                currentSession.sendMessage(new TextMessage(message));
//            } else {
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    // Envia mensagem para todas as sessões
    public void sendMessageToAll(String message) {
        TextMessage msg = new TextMessage(message);
        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(msg);
                }
            } catch (IOException e) {
                // Trata a exceção adequadamente
            }
        }
    }

}
