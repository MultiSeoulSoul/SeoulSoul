package com.multi.seoulsoul.user.tempDTO;

public class SoulDTO {
	private String locationName;
	private int slCount;
	
	@Override
	public String toString() {
		return "SoulDTO [locationName=" + locationName + ", slCount=" + slCount + "]";
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public int getSlCount() {
		return slCount;
	}
	public void setSlCount(int slCount) {
		this.slCount = slCount;
	}
	
	
}
