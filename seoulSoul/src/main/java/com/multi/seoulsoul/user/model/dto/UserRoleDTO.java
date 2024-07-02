package com.multi.seoulsoul.user.model.dto;

public class UserRoleDTO {
	private int userNo;
    private int authorityCode;
    
    public UserRoleDTO() {
	}

	public UserRoleDTO(int userNo, int authorityCode) {
		super();
		this.userNo = userNo;
		this.authorityCode = authorityCode;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	@Override
	public String toString() {
		return "UserRoleDTO [userNo=" + userNo + ", authorityCode=" + authorityCode + "]";
	}
    
}
