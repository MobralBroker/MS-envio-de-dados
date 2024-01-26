package com.alx.MSenviodedados.config;


import com.alx.MSenviodedados.handler.WSHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer { //implements WebSocketMessageBrokerConfigurer {

    public WebSocketHandler myHandler() {
        return new WSHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/chat").setAllowedOrigins("*").addInterceptors(new CustomHandshakeInterceptor());
    }

//    @Overridex
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(myHandler(), "/chat")
//                .setAllowedOrigins("*")
//                .addInterceptors(new CustomHandshakeInterceptor())
//                .setProtocolHandlers(protocolHandlerRegistry -> {
//                    protocolHandlerRegistry.addProtocolHandler("seu-subprotocolo-aqui");
//                });
//    }

}

