package com.multi.seoulsoul.achieve.model.dto;

public class AchLocaCountDTO {
	private int userNo;
	private int locationCode;
	private int curCount;
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}
	public int getCurCount() {
		return curCount;
	}
	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}
	
	@Override
	public String toString() {
		return "achLocaCountDTO [userNo=" + userNo + ", locationCode=" + locationCode + ", curCount=" + curCount + "]";
	}
	
	
}
