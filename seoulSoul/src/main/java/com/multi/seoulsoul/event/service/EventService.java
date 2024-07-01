package com.multi.seoulsoul.event.service;

import java.util.List;
import java.util.Map;

import com.multi.seoulsoul.event.model.dto.EventDTO;
import com.multi.seoulsoul.event.model.dto.ReplyDTO;

public interface EventService {
	void insertEvent(EventDTO eventDTO) throws Exception;

	void updateEventImagePath(EventDTO eventDTO) throws Exception;

	EventDTO selectEventByNo(int eventNo)throws Exception;

	List<EventDTO> selectAllEvents() throws Exception;

	void eventInsertForm(EventDTO eventDTO) throws Exception;

	void saveFile(int eventNo, String originalName, String savedName) throws Exception;

	void saveFile(Map<String, Object> fileParams) throws Exception;

	void incrementViews(int eventNo) throws Exception;

	void deleteEvent(int eventNo) throws Exception;

	void updateEvent(EventDTO eventDTO) throws Exception;

	void updateEventFile(Map<String, Object> fileParams) throws Exception;
	
	void deleteRecommend(int recommendationNo) throws Exception;
	
	void addComment(ReplyDTO reply);
	
    List<ReplyDTO> getComments(int eventNo);
    
    void updateComment(ReplyDTO reply);
    
    void deleteComment(int replyNo, int userNo);

}