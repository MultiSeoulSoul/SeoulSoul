package com.multi.seoulsoul.achieve.model.dto;

public class AchCateCountDTO {
	private int userNo;
	private AchCateDTO achCate;
	private int curCount;
	
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
	public int getCurCount() {
		return curCount;
	}
	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}
	@Override
	public String toString() {
		return "achLocaCountDTO [userNo=" + userNo + ", achCate=" + achCate + ", curCount=" + curCount + "]";
	}
}
