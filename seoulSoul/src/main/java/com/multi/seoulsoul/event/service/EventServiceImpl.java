package com.multi.seoulsoul.event.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.event.model.dao.EventDAO;
import com.multi.seoulsoul.event.model.dto.EventDTO;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDAO eventDAO;

	@Override
	public List<EventDTO> selectAllEvents() throws Exception {
		return eventDAO.selectAllEvents();
	}

	@Override
	public void eventInsertForm(EventDTO eventDTO) throws Exception {
		eventDAO.insertEvent(eventDTO);
	}

	@Override
	public void saveFile(int eventNo, String originalName, String savedName) throws Exception {
		eventDAO.insertFile(eventNo, originalName, savedName);
	}

	@Override
	public void insertEvent(EventDTO eventDTO) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEventImagePath(EventDTO eventDTO) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public EventDTO selectEventByNo(int eventNo) throws Exception {
		return eventDAO.selectEventByNo(eventNo);
	}

	@Override
	public void saveFile(Map<String, Object> fileParams) throws Exception {
		eventDAO.insertFile(fileParams);
	}

	@Override
	public void incrementViews(int eventNo) throws Exception {
		eventDAO.incrementViews(eventNo);
	}

	@Override
	@Transactional
	public void deleteEvent(int eventNo) throws Exception {
		eventDAO.deleteEvent(eventNo);
	}

	@Override
	public void updateEvent(EventDTO eventDTO) throws Exception {
	    eventDAO.updateEvent(eventDTO);
	}

	@Override
	public void updateEventFile(Map<String, Object> fileParams) throws Exception {
	    eventDAO.updateEventFile(fileParams);
	}
}
