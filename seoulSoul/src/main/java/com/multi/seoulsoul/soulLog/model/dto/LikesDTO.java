package com.multi.seoulsoul.soulLog.model.dto;

import java.sql.Timestamp;

public class LikesDTO {
	
	private int userNo;
	private int soulLogNo;
	private Timestamp likedDate;
	
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getSoulLogNo() {
		return soulLogNo;
	}
	public void setSoulLogNo(int soulLogNo) {
		this.soulLogNo = soulLogNo;
	}
	public Timestamp getLikedDate() {
		return likedDate;
	}
	public void setLikedDate(Timestamp likedDate) {
		this.likedDate = likedDate;
	}
	
	
	@Override
	public String toString() {
		return "LikesDTO [userNo=" + userNo + ", soulLogNo=" + soulLogNo + ", likedDate=" + likedDate + "]";
	}
	
}
