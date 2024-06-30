package com.multi.seoulsoul.chatbot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@PropertySource("classpath:application.properties")
@Controller
@RequestMapping("chatbot")
public class ChatbotController {

//    @Value("${clova.chatbot.client-id}")
//    private String clientId;

    @Value("${clova.chatbot.client-secret}")
    private String clientSecret;

    @Value("${clova.chatbot.api-url}")
    private String apiUrl;

    @RequestMapping("/chatbot")
    public void chatbotWindow() {

    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public Map<String, String> sendMessage(@RequestParam("message") String message) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-CHATBOT_SIGNATURE", generateSignature(message));
        headers.set("Content-Type", "application/json");

        Map<String, String> body = new HashMap<>();
        body.put("version", "v2");
        body.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2");
        body.put("timestamp", String.valueOf(System.currentTimeMillis()));
        body.put("bubbles", "[{\"type\": \"text\", \"data\": {\"description\": \"" + message + "\"}}]");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        // 네이버 클로바 챗봇 API 응답 처리 코드 작성
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("response", response.getBody());
        return responseBody;
    }

    private String generateSignature(String message) {
        // 클로바 챗봇 API 시그니처 생성 로직 작성
        return "generated-signature";
    }
}
