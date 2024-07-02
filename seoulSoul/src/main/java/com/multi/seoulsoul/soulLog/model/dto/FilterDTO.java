package com.multi.seoulsoul.soulLog.model.dto;

public class FilterDTO {
	
	private int page;
	private int start;
	private int pageCount = 10; // 10개씩
	private int startIndex;
	private int locationCode;
	private int categoryCode;
	private String searchWord;

	// start와 startIndex를 구하는 메소드
	public void setStartAndStartIndex(int page) {
		
		start = 1 + (page - 1) * pageCount;
		// 1page: 1 + 0 * 10 => start 1
		// 2page: 1 + 1 * 10 => start 11
		
		startIndex = start - 1;
		// 1page: startIndex 0
		// 2page: startIndex 10
		
	}
	

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
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
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}


	@Override
	public String toString() {
		return "FilterDTO [page=" + page + ", start=" + start + ", pageCount=" + pageCount + ", startIndex=" + startIndex
				+ ", locationCode=" + locationCode + ", categoryCode=" + categoryCode + ", searchWord=" + searchWord
				+ "]";
	}
	
}
