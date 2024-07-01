package com.multi.seoulsoul.report.model.dto;

import java.sql.Timestamp;

public class ReportReplyDTO {
	
	private int replyNo;
	private int reportNo;
	private String content;
	private Timestamp createdDate;
	
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	
	@Override
	public String toString() {
		return "ReportReplyDTO [replyNo=" + replyNo + ", reportNo=" + reportNo + ", content=" + content
				+ ", createdDate=" + createdDate + "]";
	}

}
