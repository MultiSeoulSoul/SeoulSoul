package com.multi.seoulsoul.soulLog.model.dto;

public class WriterDTO {

	private int userNo;
	private String nickname;
	
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	@Override
	public String toString() {
		return "WriterDTO [userNo=" + userNo + ", nickname=" + nickname + "]";
	}
	
}
