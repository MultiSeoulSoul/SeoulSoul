package com.multi.seoulsoul.soulLog.model.dto;

import java.sql.Timestamp;

public class SLReplyDTO {
	private int replyNo;
	private int soulLogNo;
	private String content;
	private int writer;
	private String createdDate;
	private String modifiedDate;
	private int count;
	
	public SLReplyDTO() {
	}

	public SLReplyDTO(int replyNo, int soulLogNo, String content, int writer, String createdDate, String modifiedDate,
			int count) {
		super();
		this.replyNo = replyNo;
		this.soulLogNo = soulLogNo;
		this.content = content;
		this.writer = writer;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.count = count;
	}

	@Override
	public String toString() {
		return "SLReplyDTO [replyNo=" + replyNo + ", soulLogNo=" + soulLogNo + ", content=" + content + ", writer="
				+ writer + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", count=" + count + "]";
	}

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

	public int getWriter() {
		return writer;
	}

	public void setWriter(int writer) {
		this.writer = writer;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
