package com.multi.seoulsoul.rec.model.dto;

import java.sql.Timestamp;

public class RecDTO {
    private int recommendationNo;
    private String title;
    private String content;
    private Timestamp createdDate;
    private int views;

    // Getters and Setters
    public int getRecommendationNo() {
        return recommendationNo;
    }

    public void setRecommendationNo(int recommendationNo) {
        this.recommendationNo = recommendationNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}