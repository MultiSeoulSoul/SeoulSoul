package com.multi.seoulsoul.achieve.model.dto;

public class AchCateGetDTO {
	private int userNo;
	private AchCateDTO achCate;
	private char status;
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public AchCateDTO getAchCate() {
		return achCate;
	}
	public void setAchCate(AchCateDTO achCate) {
		this.achCate = achCate;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "AchLocaGetDTO [userNo=" + userNo + ", achCate=" + achCate + ", status=" + status + "]";
	}
	
}
