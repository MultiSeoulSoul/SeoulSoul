package com.multi.seoulsoul.soulLog.model.dto;

public class PageDTO {
	
	private int page;
	private int start;
	private int pageCount = 15; // 15개씩
	private int startIndex;

	// start와 startIndex를 구하는 메소드
	public void setStartAndStartIndex(int page) {
		
		start = 1 + (page - 1) * pageCount;
		// 1page: 1 + 0 * 15 => start 1
		// 2page: 1 + 1 * 15 => start 16
		
		startIndex = start - 1;
		// 1page: startIndex 0
		// 2page: startIndex 15
		
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
	
}
