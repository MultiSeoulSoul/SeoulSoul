package com.multi.seoulsoul.rec.model.dao;

import com.multi.seoulsoul.rec.model.dto.RecDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecDAO {
    void insertRecommendation(RecDTO recDTO) throws Exception;
    List<RecDTO> selectAllRecommendations() throws Exception;
    void insertFile(@Param("recommendationNo") int recommendationNo, @Param("originalName") String originalName, @Param("savedName") String savedName) throws Exception;
    RecDTO selectRecommendationById(int recommendationNo) throws Exception;
    void incrementViews(int recommendationNo) throws Exception;
	void updateRec(RecDTO rec);
	void deleteRec(int recommendationNo);
	RecDTO selectRecByNo(int recommendationNo);
	void updateRecommend(RecDTO rec);
	void deleteRecommend(int recommendationNo);
	void deleteRecommendationFiles(int recommendationNo);
	void updateFile(Map<String, Object> paramMap);
	void addHeart(@Param("userNo") int userNo, @Param("recommendationNo") int recommendationNo) throws Exception;
    void removeHeart(@Param("userNo") int userNo, @Param("recommendationNo") int recommendationNo) throws Exception;
    boolean isHearted(@Param("userNo") int userNo, @Param("recommendationNo") int recommendationNo) throws Exception;
	
}