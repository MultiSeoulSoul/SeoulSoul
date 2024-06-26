	package com.multi.seoulsoul.rec.model.dto;

import java.util.Date;

public class RecDTO {
    private int recommendationNo;
    private String title;
    private String content;
    private int views;
    private String imagePath; // 이미지 경로 추가
    private Date createdDate;

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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    
    @Override
    public String toString() {
        return "RecDTO{" +
                "recommendationNo=" + recommendationNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", views=" + views +
                ", imagePath='" + imagePath + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}