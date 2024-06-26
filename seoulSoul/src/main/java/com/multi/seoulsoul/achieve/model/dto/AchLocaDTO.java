package com.multi.seoulsoul.achieve.model.dto;

import java.sql.Timestamp;

public class AchieveDTO {
	private int achNo;
	private int locationCode;
	private int categoryCode;
	private int maxCount;
	private String title;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	
	
	public int getAchNo() {
		return achNo;
	}
	public void setAchNo(int achNo) {
		this.achNo = achNo;
	}
	public int getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "AchieveDTO [achNo=" + achNo + ", locationCode=" + locationCode + ", categoryCode=" + categoryCode
				+ ", maxCount=" + maxCount + ", title=" + title + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}
	
	
	
}
