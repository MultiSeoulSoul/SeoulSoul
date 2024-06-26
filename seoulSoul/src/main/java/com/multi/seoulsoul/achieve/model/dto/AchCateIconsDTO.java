package com.multi.seoulsoul.achieve.model.dto;

public class AchCateIconsDTO {
	private int iconNo;
	private AchCateDTO achCate;
	private String originalName;
	private String savedName;
	
	public int getIconNo() {
		return iconNo;
	}
	public void setIconNo(int iconNo) {
		this.iconNo = iconNo;
	}
	public AchCateDTO getAchCate() {
		return achCate;
	}
	public void setAchCate(AchCateDTO achCate) {
		this.achCate = achCate;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getSavedName() {
		return savedName;
	}
	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}
	
	@Override
	public String toString() {
		return "AchLocaIconsDTO [iconNo=" + iconNo + ", achCate=" + achCate + ", originalName=" + originalName
				+ ", savedName=" + savedName + "]";
	}
	
}
