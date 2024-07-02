package com.multi.seoulsoul.user.tempDTO;

import java.sql.Timestamp;

public class RecHeartBtnDTO {

	private String recommendationNo;
	private String title;
	private Timestamp createdDate;
	private int totalCount;
	
	public String getRecommendationNo() {
		return recommendationNo;
	}
	public void setRecommendationNo(String recommendationNo) {
		this.recommendationNo = recommendationNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getBoardCreatedDate() {
		return createdDate;
	}
	public void setBoardCreatedDate(Timestamp boardCreatedDate) {
		this.createdDate = boardCreatedDate;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
