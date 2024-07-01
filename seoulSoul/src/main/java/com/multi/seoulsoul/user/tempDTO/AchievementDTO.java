package com.multi.seoulsoul.user.tempDTO;

public class AchievementDTO {
    private String savedName;
    private String title;
    private int maxCount;
    private int curCount;
    private char status;
    private String conditionName;
    private String type; // loca 또는 cate 구분을 위해

    public AchievementDTO() {}

	@Override
	public String toString() {
		return "AchievementDTO [savedName=" + savedName + ", title=" + title + ", maxCount=" + maxCount + ", curCount="
				+ curCount + ", status=" + status + ", conditionName=" + conditionName + ", type=" + type + "]";
	}

	public String getSavedName() {
		return savedName;
	}

	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public int getCurCount() {
		return curCount;
	}

	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
