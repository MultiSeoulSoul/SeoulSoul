package com.multi.seoulsoul.cs.model.dto;

import java.util.Date;

public class CsAnswerDTO {
	
    private int answerNo;
    private int questionNo;
    private String content;
    private int writer;
    private Date createdDate;
    
    public CsAnswerDTO(int answerNo, int questionNo, String content, int writer, Date createdDate) {
    	this.answerNo = answerNo;
    	this.questionNo = questionNo;
    	this.content = content;
    	this.writer = writer;
    	this.createdDate = createdDate;
    }

    public int getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(int answerNo) {
        this.answerNo = answerNo;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

	@Override
	public String toString() {
		return "CsAnswerDTO [answerNo=" + answerNo + ", questionNo=" + questionNo + ", content=" + content + ", writer="
				+ writer + ", createdDate=" + createdDate + "]";
	}

}
