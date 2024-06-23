package com.multi.seoulsoul.event.model.dao;

import com.multi.seoulsoul.event.model.dto.EventDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventDAO {
    void insertEvent(EventDTO eventDTO) throws Exception;
}