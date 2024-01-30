package com.alx.MSenviodedados.config;


import com.alx.MSenviodedados.handler.WSDashboardHandler;
import com.alx.MSenviodedados.handler.WSHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    public WebSocketHandler myHandler() {
        return new WSHandler();
    }
    public WebSocketHandler myHandler2() {
        return new WSDashboardHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/chat").setAllowedOrigins("*").addInterceptors(new CustomHandshakeInterceptor());
        registry.addHandler(myHandler2(), "/dash").setAllowedOrigins("*").addInterceptors(new CustomHandshakeInterceptor());

    }

}

