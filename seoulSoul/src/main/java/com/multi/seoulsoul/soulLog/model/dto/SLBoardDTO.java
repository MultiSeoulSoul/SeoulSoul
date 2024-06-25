package com.multi.seoulsoul.soulLog.model.DTO;

public class SLBoardDTO {
    private int soulLogNo;         // soul_log_no
    private int locationCode;      // location_code
    private int categoryCode;      // category_code
    private String title;          // title
    private String content;        // content
    private int writer;            // writer
    private int views;             // views
    private String createdDate;    // created_date
    private String modifiedDate;   // modified_date
    
    public SLBoardDTO() {
	}

	public SLBoardDTO(int soulLogNo, int locationCode, int categoryCode, String title, String content, int writer,
			int views, String createdDate, String modifiedDate) {
		super();
		this.soulLogNo = soulLogNo;
		this.locationCode = locationCode;
		this.categoryCode = categoryCode;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.views = views;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public int getSoulLogNo() {
		return soulLogNo;
	}

	public void setSoulLogNo(int soulLogNo) {
		this.soulLogNo = soulLogNo;
	}

	public int getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
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

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
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

	@Override
	public String toString() {
		return "SLBoardDTO [soulLogNo=" + soulLogNo + ", locationCode=" + locationCode + ", categoryCode="
				+ categoryCode + ", title=" + title + ", content=" + content + ", writer=" + writer + ", views=" + views
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
    
}
