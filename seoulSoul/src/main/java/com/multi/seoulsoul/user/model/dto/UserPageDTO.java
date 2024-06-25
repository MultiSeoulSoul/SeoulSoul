package com.multi.seoulsoul.user.model.dto;

public class UserPageDTO {
	private int userNo;
	private int start;
	private int end;
	private int page;

	public void setStartEnd(int page) {
		start = 1 + (page - 1) * 10;
		end = page * 10;
	}
	
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "UserPageDTO [userNo=" + userNo + ", start=" + start + ", end=" + end + ", page=" + page + "]";
	}

}
