package com.multi.seoulsoul.report.model.dto;

public class ReportedSoulLogDTO {

	private int soulLogNo;
	private String reportedTitle;
	
	
	public int getSoulLogNo() {
		return soulLogNo;
	}
	public void setSoulLogNo(int soulLogNo) {
		this.soulLogNo = soulLogNo;
	}
	public String getTitle() {
		return reportedTitle;
	}
	public void setTitle(String reportedTitle) {
		this.reportedTitle = reportedTitle;
	}
	
	
	@Override
	public String toString() {
		return "ReportedSoulLogDTO [soulLogNo=" + soulLogNo + ", reportedTitle=" + reportedTitle + "]";
	}
	
}
