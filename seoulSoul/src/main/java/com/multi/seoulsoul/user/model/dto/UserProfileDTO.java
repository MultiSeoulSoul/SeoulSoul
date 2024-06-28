package com.multi.seoulsoul.user.model.dto;

public class UserProfileDTO {
	private int userNo;
    private String profileContent;
    private String profilePicName;
    
    private String nickname;
    
    public UserProfileDTO() {
	}

	@Override
	public String toString() {
		return "UserProfileDTO [userNo=" + userNo + ", profileContent=" + profileContent + ", profilePicName="
				+ profilePicName + ", nickname=" + nickname + "]";
	}

	public UserProfileDTO(int userNo, String profileContent, String profilePicName, String nickname) {
		super();
		this.userNo = userNo;
		this.profileContent = profileContent;
		this.profilePicName = profilePicName;
		this.nickname = nickname;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
}
