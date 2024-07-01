package com.multi.seoulsoul.user.tempDTO;

import java.sql.Timestamp;

public class RecHeartBtnDTO {

	private String title;
	private Timestamp createdDate;

	// 재식 추가
	private int totalCount;

	public RecHeartBtnDTO() {
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
