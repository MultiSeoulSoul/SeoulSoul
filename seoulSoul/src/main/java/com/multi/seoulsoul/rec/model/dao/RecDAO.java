package com.multi.seoulsoul.rec.model.dao;

import com.multi.seoulsoul.rec.model.dto.RecDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecDAO {
    void insertRecommendation(RecDTO recDTO) throws Exception;
}