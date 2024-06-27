package com.multi.seoulsoul.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.multi.seoulsoul.event.model.dao.EventDAO;
import com.multi.seoulsoul.event.model.dto.EventDTO;
import java.util.List;
import java.util.Map;

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
}