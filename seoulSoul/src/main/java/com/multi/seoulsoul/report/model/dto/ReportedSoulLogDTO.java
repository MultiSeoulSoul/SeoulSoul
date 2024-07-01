package com.multi.seoulsoul.report.model.dto;

public class ReportedSoulLogDTO {

	private int soulLogNo;
	private String title;
	
	
	public int getSoulLogNo() {
		return soulLogNo;
	}
	public void setSoulLogNo(int soulLogNo) {
		this.soulLogNo = soulLogNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@Override
	public String toString() {
		return "ReportedSoulLogDTO [soulLogNo=" + soulLogNo + ", title=" + title + "]";
	}
	
}
