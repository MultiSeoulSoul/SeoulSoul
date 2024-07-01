package com.multi.seoulsoul.cs.model.dto;

public class CsCategoryDTO {

	private int categoryCode;
    private String categoryName;

    public CsCategoryDTO() {
    	
    }
    
    public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CsCategoryDTO [categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
	}
    
}
