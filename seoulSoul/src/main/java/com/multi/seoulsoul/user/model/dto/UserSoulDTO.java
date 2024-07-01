package com.multi.seoulsoul.user.model.dto;

public class UserSoulDTO {
	private int userNo;
    private int locationCode;
    private int soulExp;
    
    public UserSoulDTO() {
	}

	public UserSoulDTO(int userNo, int locationCode, int soulExp) {
		super();
		this.userNo = userNo;
		this.locationCode = locationCode;
		this.soulExp = soulExp;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}

	public int getSoulExp() {
		return soulExp;
	}

	public void setSoulExp(int soulExp) {
		this.soulExp = soulExp;
	}

	@Override
	public String toString() {
		return "UserSoulDTO [userNo=" + userNo + ", locationCode=" + locationCode + ", soulExp=" + soulExp + "]";
	}
    
}
