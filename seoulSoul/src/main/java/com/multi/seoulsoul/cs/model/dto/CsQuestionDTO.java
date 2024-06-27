package com.multi.seoulsoul.cs.model.dto;

import java.sql.Timestamp;
import java.util.List;

public class CsQuestionDTO {
	
    private int questionNo;
    private int categoryCode;
    private String title;
    private String content;
    private int writer;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String isAnswered;
    private int views;
    
    private List<CsCategoryDTO> categorys;
    private List<CsWriterDTO> writers;
    private List<CsQuestionFileDTO> files;
    private List<CsAnswerDTO> answers;
	
    public CsQuestionDTO() {
    	
    }
    
    public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getIsAnswered() {
		return isAnswered;
	}
	public void setIsAnswered(String isAnswered) {
		this.isAnswered = isAnswered;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public List<CsCategoryDTO> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<CsCategoryDTO> categorys) {
		this.categorys = categorys;
	}
	public List<CsWriterDTO> getWriters() {
		return writers;
	}

	public void setWriters(List<CsWriterDTO> writers) {
		this.writers = writers;
	}

	public List<CsQuestionFileDTO> getFiles() {
		return files;
	}
	public void setFiles(List<CsQuestionFileDTO> files) {
		this.files = files;
	}
	public List<CsAnswerDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<CsAnswerDTO> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "CsQuestionDTO [questionNo=" + questionNo + ", categoryCode=" + categoryCode + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", isAnswered=" + isAnswered + ", views=" + views + ", categorys=" + categorys
				+ ", writers=" + writers + ", files=" + files + ", answers=" + answers + "]";
	}
	
}

