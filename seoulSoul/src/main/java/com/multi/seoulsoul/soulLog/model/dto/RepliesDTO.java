package com.multi.seoulsoul.soulLog.model.dto;

import java.sql.Date;

public class RepliesDTO {
	
	private int replyNo;
	private int soulLogNo;
	private String content;
	private WriterDTO writer;
	private int repliesCount;
	private Date createdDate;
	private Date modifiedDate;
	
	
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
	public WriterDTO getWriter() {
		return writer;
	}
	public void setWriter(WriterDTO writer) {
		this.writer = writer;
	}
	public int getRepliesCount() {
		return repliesCount;
	}
	public void setRepliesCount(int repliesCount) {
		this.repliesCount = repliesCount;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	@Override
	public String toString() {
		return "RepliesDTO [replyNo=" + replyNo + ", soulLogNo=" + soulLogNo + ", content=" + content + ", writer="
				+ writer + ", repliesCount=" + repliesCount + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}
	
}
