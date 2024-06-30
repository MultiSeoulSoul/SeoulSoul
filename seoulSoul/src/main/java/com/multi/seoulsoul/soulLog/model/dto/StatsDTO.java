package com.multi.seoulsoul.soulLog.model.dto;

public class StatsDTO {

	private int locationCode;
	private String locationName;
	private int soulLogCount;
	private int likeCount;
	private int replyCount;
	private int exp;
	
	public int getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public int getSoulLogCount() {
		return soulLogCount;
	}
	public void setSoulLogCount(int soulLogCount) {
		this.soulLogCount = soulLogCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
	@Override
	public String toString() {
		return "StatsDTO [locationCode=" + locationCode + ", locationName=" + locationName + ", soulLogCount="
				+ soulLogCount + ", likeCount=" + likeCount + ", replyCount=" + replyCount + ", exp=" + exp + "]";
	}
	
}
