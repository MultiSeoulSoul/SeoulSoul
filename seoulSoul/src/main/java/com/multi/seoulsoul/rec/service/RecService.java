package com.multi.seoulsoul.rec.service;

import com.multi.seoulsoul.rec.model.dto.RecDTO;
import java.util.List;

public interface RecService {
    void recInsertForm(RecDTO recDTO) throws Exception;
    List<RecDTO> selectAllRecommendations() throws Exception;
    void saveFile(int recommendationNo, String originalName, String savedName) throws Exception;
    RecDTO getRecommendationById(int recommendationNo) throws Exception;
	void saveRecommendation(RecDTO recDTO);
	RecDTO selectRecommendationByNo(int recommendationNo) throws Exception;
	void insertRecommendation(RecDTO recDTO);
	void increaseViews(int recommendationNo);
	RecDTO selectRecommendationByNo(String recommendationNo);
	RecDTO selectRecommendationById(int recommendationNo) throws Exception;
	void incrementViews(int recommendationNo) throws Exception;
}