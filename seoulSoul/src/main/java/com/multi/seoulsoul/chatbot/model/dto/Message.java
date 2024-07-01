package com.multi.seoulsoul.chatbot.model.dto;

//브라우저에서 채팅창에 넣은 데이터를 서버에서 받을 때 사용할 vo
public class Message {

		private String from;
		private String text;
		
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}

}
