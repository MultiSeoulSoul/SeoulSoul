package com.multi.seoulsoul.soulLog.model.dto;

public class FilesDTO {
	
	private int fileNo;
	private int soulLogNo;
	private String originalName;
	private String savedName;
	
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getSoulLogNo() {
		return soulLogNo;
	}
	public void setSoulLogNo(int soulLogNo) {
		this.soulLogNo = soulLogNo;
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
		return "FilesDTO [fileNo=" + fileNo + ", soulLogNo=" + soulLogNo + ", originalName=" + originalName
				+ ", savedName=" + savedName + "]";
	}

}
