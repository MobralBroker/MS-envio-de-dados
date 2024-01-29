package com.alx.MSenviodedados.config;

import com.alx.MSenviodedados.exceptions.ApiRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;


public class CustomHandshakeInterceptor implements HandshakeInterceptor {
    @Value("${apiAutenticacao.path}")
    private String pathAutenticacao;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String headerToken = request.getHeaders().getFirst("cookie");
        String token=null;

        int startPosition = headerToken.indexOf("X-Authorization=");
        String result = headerToken.substring(startPosition + "X-Authorization=".length());

        System.out.println("headerToken :::: " + headerToken);
        System.out.println("result :::: " + result);
        token = result;

        if (token == null || !validateToken(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;

        }else{
            return true;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }

    private boolean validateToken(String token) {
        try {
            ResponseEntity<String> responseAuth =  new RestTemplate().getForEntity("http://localhost:8080/auth/validar?token="+ token, String.class);
            if(responseAuth.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
                throw new ApiRequestException(token);
            }
            System.out.println(" validate token true");

            return true;

        }catch (Exception e){

            System.out.println(e);
            System.out.println(" validate token false");

            return false;
        }
    }
}