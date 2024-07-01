package com.multi.seoulsoul.soulLog.model.dto;

import java.sql.Timestamp;

public class RepliesDTO {
	
	private int replyNo;
	private int soulLogNo;
	private String content;
	private ReplyWriterDTO writer;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getSoulLogNo() {
		return soulLogNo;
	}
	public void setSoulLogNo(int soulLogNo) {
		this.soulLogNo = soulLogNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ReplyWriterDTO getWriter() {
		return writer;
	}
	public void setWriter(ReplyWriterDTO writer) {
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
	
	
	@Override
	public String toString() {
		return "RepliesDTO [replyNo=" + replyNo + ", soulLogNo=" + soulLogNo + ", content=" + content + ", writer="
				+ writer + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
}
