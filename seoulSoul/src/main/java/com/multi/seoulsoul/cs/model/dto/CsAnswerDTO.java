package com.multi.seoulsoul.cs.model.dto;

import java.sql.Timestamp;

public class CsAnswerDTO {
	
    private int answerNo;
    private int questionNo;
    private String content;
    private int writer;
    private Timestamp createdDate;
    
    private CsWriterDTO writerInfo; //단일 작성자 정보
    
    public CsAnswerDTO() {
    	
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

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public CsWriterDTO getWriterInfo() {
		return writerInfo;
	}

	public void setWriterInfo(CsWriterDTO writerInfo) {
		this.writerInfo = writerInfo;
	}

	@Override
	public String toString() {
		return "CsAnswerDTO [answerNo=" + answerNo + ", questionNo=" + questionNo + ", content=" + content + ", writer="
				+ writer + ", createdDate=" + createdDate + ", writerInfo=" + writerInfo + "]";
	}

}
