package com.multi.seoulsoul.soulLog.model.dto;

public class LocationDTO {

	private int locationCode;
	private String locationName;
	
	
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
	
	
	@Override
	public String toString() {
		return "LocationDTO [locationCode=" + locationCode + ", locationName=" + locationName + "]";
	}
	
}
