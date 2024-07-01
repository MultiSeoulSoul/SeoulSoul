package com.multi.seoulsoul.achieve.model.dto;

public class AchLocaCountDTO {
	private int userNo;
	private AchLocaDTO achLoca;
	private int curCount;
	
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
	public int getCurCount() {
		return curCount;
	}
	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}
	
	@Override
	public String toString() {
		return "achLocaCountDTO [userNo=" + userNo + ", achLoca=" + achLoca + ", curCount=" + curCount + "]";
	}
	
	
}
