package com.multi.seoulsoul.soulLog.model.dto;

import java.sql.Timestamp;
import java.util.List;

public class SoulLogDTO {

	private int soulLogNo;
	private LocationDTO location;
	private CategoryDTO category;
	private String title;
	private String content;
	private WriterDTO writer;
	private int views;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	private List<FilesDTO> files;
	private List<LikesDTO> likes;
	private List<RepliesDTO> replies;
	
	
	public int getSoulLogNo() {
		return soulLogNo;
	}
	public void setSoulLogNo(int soulLogNo) {
		this.soulLogNo = soulLogNo;
	}
	public LocationDTO getLocation() {
		return location;
	}
	public void setLocation(LocationDTO location) {
		this.location = location;
	}
	public CategoryDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
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
	public WriterDTO getWriter() {
		return writer;
	}
	public void setWriter(WriterDTO writer) {
		this.writer = writer;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
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
	public List<FilesDTO> getFiles() {
		return files;
	}
	public void setFiles(List<FilesDTO> files) {
		this.files = files;
	}
	public List<LikesDTO> getLikes() {
		return likes;
	}
	public void setLikes(List<LikesDTO> likes) {
		this.likes = likes;
	}
	public List<RepliesDTO> getReplies() {
		return replies;
	}
	public void setReplies(List<RepliesDTO> replies) {
		this.replies = replies;
	}
	
	
	@Override
	public String toString() {
		return "SoulLogDTO [soulLogNo=" + soulLogNo + ", location=" + location + ", category=" + category + ", title="
				+ title + ", content=" + content + ", writer=" + writer + ", views=" + views + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", files=" + files + ", likes=" + likes
				+ ", replies=" + replies + "]";
	}
	
}
