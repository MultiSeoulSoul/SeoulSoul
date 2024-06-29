package com.multi.seoulsoul.user.model.dto;

import java.sql.Timestamp;

public class UserDTO {
    private int userNo;
    private String userId;
    private String userPw;
    private String nickname;
    private String phone;
    private String email;
    private char blacklistStatus;
    private Timestamp createdDate;
    
    private String profileContent;
    private String profilePicName;
    
    public UserDTO() {
	}

	@Override
	public String toString() {
		return "UserDTO [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", nickname=" + nickname
				+ ", phone=" + phone + ", email=" + email + ", blacklistStatus=" + blacklistStatus + ", createdDate="
				+ createdDate + ", profileContent=" + profileContent + ", profilePicName=" + profilePicName + "]";
	}

	public UserDTO(int userNo, String userId, String userPw, String nickname, String phone, String email,
			char blacklistStatus, Timestamp createdDate, String profileContent, String profilePicName) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.nickname = nickname;
		this.phone = phone;
		this.email = email;
		this.blacklistStatus = blacklistStatus;
		this.createdDate = createdDate;
		this.profileContent = profileContent;
		this.profilePicName = profilePicName;
	}

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

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getBlacklistStatus() {
		return blacklistStatus;
	}

	public void setBlacklistStatus(char blacklistStatus) {
		this.blacklistStatus = blacklistStatus;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getProfileContent() {
		return profileContent;
	}

	public void setProfileContent(String profileContent) {
		this.profileContent = profileContent;
	}

	public String getProfilePicName() {
		return profilePicName;
	}

	public void setProfilePicName(String profilePicName) {
		this.profilePicName = profilePicName;
	}
    
}
