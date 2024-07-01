package com.multi.seoulsoul.achieve.model.dto;

import java.sql.Timestamp;

public class AchLocaDTO {
    private int achNo;
    private int locationCode;
    private int maxCount;
    private String title;
    private String savedName;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    // Getter and Setter methods
    public int getAchNo() {
        return achNo;
    }
    public void setAchNo(int achNo) {
        this.achNo = achNo;
    }
    public int getLocationCode() {
        return locationCode;
    }
    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }
    public int getMaxCount() {
        return maxCount;
    }
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSavedName() {
        return savedName;
    }
    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "AchLocaDTO [achNo=" + achNo + ", locationCode=" + locationCode + ", maxCount=" + maxCount + ", title=" + title + ", savedName=" + savedName + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
    }
}
