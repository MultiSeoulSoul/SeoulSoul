package com.multi.seoulsoul.soulLog.model.dto;

import java.sql.Date;

public class LikesDTO {
	
	private int userNo;
	private int soulLogNo;
	private int likesCount;
	private Date likedDate;
	
	
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
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	public Date getLikedDate() {
		return likedDate;
	}
	public void setLikedDate(Date likedDate) {
		this.likedDate = likedDate;
	}
	
	
	@Override
	public String toString() {
		return "LikesDTO [userNo=" + userNo + ", soulLogNo=" + soulLogNo + ", likesCount=" + likesCount + ", likedDate="
				+ likedDate + "]";
	}

}
