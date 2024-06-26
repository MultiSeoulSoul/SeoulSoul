package com.multi.seoulsoul.achieve.model.dto;

public class AchLocaIconsDTO {
	private int iconNo;
	private AchLocaDTO achLoca;
	private String originalName;
	private String savedName;
	
	public int getIconNo() {
		return iconNo;
	}
	public void setIconNo(int iconNo) {
		this.iconNo = iconNo;
	}
	public AchLocaDTO getAchLoca() {
		return achLoca;
	}
	public void setAchCate(AchLocaDTO achLoca) {
		this.achLoca = achLoca;
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
		return "AchLocaIconsDTO [iconNo=" + iconNo + ", achLoca=" + achLoca + ", originalName=" + originalName
				+ ", savedName=" + savedName + "]";
	}
	
}
