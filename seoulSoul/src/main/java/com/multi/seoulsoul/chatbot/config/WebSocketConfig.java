package com.multi.seoulsoul.chatbot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //채팅방 이름 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	//접두어
    	config.enableSimpleBroker("/topic"); // /topic 이라는 주제에 대한 메시지 브로커 설정
    	config.setApplicationDestinationPrefixes("/app"); // 클라이언트에서 메시지를 보낼 때 사용할 prefix 설정
    }
    
    //채팅 내용을 보낼 주소(endPoint == url)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	registry.addEndpoint("/chat"); //자바 소켓 통신 가능 //-->WebSocketController.java @MessageMapping("/sendMessage")
    	registry.addEndpoint("/chat").withSockJS(); // SockJS를 통한 WebSocket 연결 설정
    }
}
