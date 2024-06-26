package com.multi.seoulsoul.rec.model.dao;

import com.multi.seoulsoul.rec.model.dto.RecDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecDAO {
    void insertRecommendation(RecDTO recDTO) throws Exception;
    List<RecDTO> selectAllRecommendations() throws Exception;
    void insertFile(@Param("recommendationNo") int recommendationNo, @Param("originalName") String originalName, @Param("savedName") String savedName) throws Exception;
    RecDTO selectRecommendationById(int recommendationNo) throws Exception;
    void incrementViews(int recommendationNo) throws Exception;
}