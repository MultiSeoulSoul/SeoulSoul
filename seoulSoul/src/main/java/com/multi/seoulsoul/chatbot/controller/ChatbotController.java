package com.multi.seoulsoul.chatbot.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.seoulsoul.chatbot.model.dto.Message;
import com.multi.seoulsoul.chatbot.model.dto.OutputMessage;

@Controller
@RequestMapping("chatbot")
public class ChatbotController {

	@RequestMapping("/chatbot")
	public void chatbotWindow() {
	
	}
	
	@MessageMapping("/chatbot") //채팅 내용 받을 때 사용하는 주소 
    @SendTo("/topic/messages")
	public OutputMessage send(Message message) {

		OutputMessage out = new OutputMessage();
		String menu = "";
		switch (message.getText()) {
		case "1":
			menu = "";
			break;
		case "2":
			menu = "";
			break;
		case "20":
			menu = "";
			break;
		case "10":
			menu = "";
			break;
		case "100":
			menu = "";
			break;
		case "1000":
			menu = "";
			break;
		default:
			menu = "선택한 번호는 없는 메뉴입니다. 다시 입력해주세요.";
			break;
		}
		out.setMenu(menu);
		return out;
	}	
	
}
