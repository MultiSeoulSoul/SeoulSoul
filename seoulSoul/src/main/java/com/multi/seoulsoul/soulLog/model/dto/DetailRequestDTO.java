package com.multi.seoulsoul.soulLog.model.dto;

public class DetailRequestDTO {
	
	private int soulLogNo;
	private int loginUserNo;
	
	
	public int getSoulLogNo() {
		return soulLogNo;
	}
	public void setSoulLogNo(int soulLogNo) {
		this.soulLogNo = soulLogNo;
	}
	public int getLoginUserNo() {
		return loginUserNo;
	}
	public void setLoginUserNo(int loginUserNo) {
		this.loginUserNo = loginUserNo;
	}
	
	
	@Override
	public String toString() {
		return "DetailRequestDTO [soulLogNo=" + soulLogNo + ", loginUserNo=" + loginUserNo + "]";
	}

}
