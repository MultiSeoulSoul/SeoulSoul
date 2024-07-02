package com.multi.seoulsoul.achieve.model.dto;

public class AchCateCountDTO {
	private int userNo;
	private int categoryCode;
	private int curCount;
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public int getCurCount() {
		return curCount;
	}
	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}
	@Override
	public String toString() {
		return "achLocaCountDTO [userNo=" + userNo + ", categoryCode=" + categoryCode + ", curCount=" + curCount + "]";
	}
}
