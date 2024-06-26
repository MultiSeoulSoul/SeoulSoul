package com.multi.seoulsoul.achieve.model.dto;

public class AchLocaGetDTO {
	private int userNo;
	private AchLocaDTO achLoca;
	private char status;
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public AchLocaDTO getAchLoca() {
		return achLoca;
	}
	public void setAchCate(AchLocaDTO achLoca) {
		this.achLoca = achLoca;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "AchLocaGetDTO [userNo=" + userNo + ", achLoca=" + achLoca + ", status=" + status + "]";
	}
	
}
