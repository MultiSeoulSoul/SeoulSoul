package com.multi.seoulsoul.achieve.model.dto;

import java.sql.Timestamp;
import java.util.List;

public class AdminUserListDTO {
	private int userNo;
    private String userId;
    private String nickname;
    private List<StatsDTO> userStats;
    private char blacklistStatus;
	private Timestamp lastSoulLogDate;
    
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    public List<StatsDTO> getUserStats() {
		return userStats;
	}
	public void setUserStats(List<StatsDTO> userStats) {
		this.userStats = userStats;
	}
	public char getBlacklistStatus() {
		return blacklistStatus;
	}
	public void setBlacklistStatus(char blacklistStatus) {
		this.blacklistStatus = blacklistStatus;
	}
	public Timestamp getLastSoulLogDate() {
		return lastSoulLogDate;
	}
	public void setLastSoulLogDate(Timestamp lastSoulLogDate) {
		this.lastSoulLogDate = lastSoulLogDate;
	}
	
	@Override
	public String toString() {
		return "AdminUserListDTO [userNo=" + userNo + ", userId=" + userId + ", nickname=" + nickname + ", userStats="
				+ userStats + ", blacklistStatus=" + blacklistStatus + ", lastSoulLogDate=" + lastSoulLogDate + "]";
	}
}
