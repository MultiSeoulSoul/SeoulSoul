package com.multi.seoulsoul.rec.service;

import com.multi.seoulsoul.rec.model.dto.RecDTO;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface RecService {
    void recInsertForm(RecDTO recDTO) throws Exception;
    List<RecDTO> selectAllRecommendations() throws Exception;
    void saveFile(int recommendationNo, String originalName, String savedName) throws Exception;
    RecDTO getRecommendationById(int recommendationNo) throws Exception;
	void saveRecommendation(RecDTO recDTO);
	RecDTO selectRecommendationByNo(int recommendationNo) throws Exception;
	void insertRecommendation(RecDTO recDTO) throws Exception;
	void increaseViews(int recommendationNo);
	RecDTO selectRecommendationByNo(String recommendationNo);
	RecDTO selectRecommendationById(int recommendationNo) throws Exception;
	void incrementViews(int recommendationNo) throws Exception;
	RecDTO selectRecByNo(int recommendationNo);
	void updateRec(RecDTO rec, MultipartFile image);
	void deleteRec(int recommendationNo);
	void updateRec(RecDTO rec);
	void updateRecommend(RecDTO rec) throws Exception;
	void deleteRecommend(int recommendationNo) throws Exception;
	void updateFile(Map<String, Object> fileParams) throws Exception;
	void updateFile(int recommendationNo, String originalFilename, String savedFilename) throws Exception;
	boolean toggleHeart(int userNo, int recommendationNo) throws Exception;
	boolean isHearted(int userNo, int recommendationNo) throws Exception;
	
}