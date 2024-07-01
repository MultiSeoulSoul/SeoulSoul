package com.multi.seoulsoul.user.tempDTO;

import java.sql.Timestamp;

public class ReportDTO {
	
	private int reportNo;
	private String title;
	private String reason;
	private Timestamp createdDate;
	
	// 재식 추가
	private int totalCount;
	private String reportReply;
	
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getReportReply() {
		return reportReply;
	}
	public void setReportReply(String reportReply) {
		this.reportReply = reportReply;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}