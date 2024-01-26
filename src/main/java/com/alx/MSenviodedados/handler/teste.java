package com.alx.MSenviodedados.handler;

import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public interface teste {
    Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
}
