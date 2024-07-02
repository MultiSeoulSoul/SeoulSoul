package com.multi.seoulsoul.event.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.multi.seoulsoul.event.model.dto.EventDTO;
import com.multi.seoulsoul.event.model.dto.ReplyDTO;

@Mapper
public interface EventDAO {
	void insertEvent(EventDTO eventDTO) throws Exception;

	void insertFile(int eventNo, String originalName, String savedName) throws Exception;

	List<EventDTO> selectAllEvents() throws Exception;

	void updateEventImagePath(EventDTO eventDTO) throws Exception;

	void insertFile(Map<String, Object> fileParams);

	void incrementViews(int eventNo) throws Exception;

	EventDTO selectEventByNo(int eventNo) throws Exception;

	void updateEvent(EventDTO eventDTO);

	void deleteEvent(int eventNo) throws Exception;

	void updateEventImage(Map<String, Object> fileParams);

	void updateEventFile(Map<String, Object> fileParams);

	void deleteEventFiles(int eventNo) throws Exception;

	void insertComment(ReplyDTO reply);

	List<ReplyDTO> selectCommentsByEventNo(int eventNo);

	void updateComment(ReplyDTO reply);

	void deleteComment(Map<String, Integer> params);

}