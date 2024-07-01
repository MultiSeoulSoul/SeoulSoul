package com.multi.seoulsoul.user.tempDTO;

import java.sql.Timestamp;

public class LikesDTO {
	
	private int userNo;
	private int soulLogNo;
	private Timestamp likedDate;
	
	// 재식 추가
	private int totalCount;
    private String locationName;
    private String categoryName;
    private String title;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "LikesDTO [userNo=" + userNo + ", soulLogNo=" + soulLogNo + ", likedDate=" + likedDate + "]";
	}
	
}