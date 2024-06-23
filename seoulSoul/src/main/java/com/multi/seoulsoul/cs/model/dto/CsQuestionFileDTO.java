package com.multi.seoulsoul.cs.model.dto;

public class CsQuestionFileDTO {
	
    private int fileNo;
    private int questionNo;
    private String originalFileName;
    private String storedFileName;
    private String filePath;

    public CsQuestionFileDTO(int fileNo, int questionNo, String originalFileName, String storedFileName, String filePath) {
    	this.fileNo = fileNo;
    	this.questionNo = questionNo;
    	this.originalFileName = originalFileName;
    	this.storedFileName = storedFileName;
    	this.filePath = filePath;
    }
    
    public int getFileNo() {
        return fileNo;
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

	@Override
	public String toString() {
		return "CsQuestionFileDTO [fileNo=" + fileNo + ", questionNo=" + questionNo + ", originalFileName="
				+ originalFileName + ", storedFileName=" + storedFileName + ", filePath=" + filePath + "]";
	}
    
}
