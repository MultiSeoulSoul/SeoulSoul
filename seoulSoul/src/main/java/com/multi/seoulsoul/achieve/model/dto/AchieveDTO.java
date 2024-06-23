package com.multi.seoulsoul.achieve.model.dto;

import java.time.LocalDateTime;

public class AchieveDTO {
	private int achNo;
	private int locationCode;
	private int count;
	private String title;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Override
	public String toString() {
		return "AchieveDTO [achNo=" + achNo + ", locationCode=" + locationCode + ", count=" + count
				+ ", title=" + title + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}
	
	
}
