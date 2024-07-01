package com.multi.seoulsoul.user.model.dto;

public class UserAuthorityDTO {
	private int authorityCode;
    private String authorityName;
    private String authorityDesc;
    
    public UserAuthorityDTO() {
	}

	public UserAuthorityDTO(int authorityCode, String authorityName, String authorityDesc) {
		super();
		this.authorityCode = authorityCode;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	@Override
	public String toString() {
		return "UserAuthorityDTO [authorityCode=" + authorityCode + ", authorityName=" + authorityName
				+ ", authorityDesc=" + authorityDesc + "]";
	}
    
}
