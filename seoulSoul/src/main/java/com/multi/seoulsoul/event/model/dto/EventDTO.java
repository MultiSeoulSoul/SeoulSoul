package com.multi.seoulsoul.event.model.dto;

import java.sql.Timestamp;

public class EventDTO {
	private int eventNo;
	private String title;
	private String content;
	private Timestamp startDate;
	private Timestamp endDate;
	private int views;
	private Timestamp createdDate;
	private String address; // address 항목 추가
	private String originalFilename; // 추가
	private String imagePath;

	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "EventDTO{" + "eventNo=" + eventNo + ", title='" + title + '\'' + ", content='" + content + '\''
				+ ", address='" + address + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + ", views="
				+ views + ", createdDate=" + createdDate + ", originalFilename='" + originalFilename + '\''
				+ ", imagePath='" + imagePath + '\'' + '}';
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}