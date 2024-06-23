package com.multi.seoulsoul.event.service;

import com.multi.seoulsoul.event.model.dao.EventDAO;
import com.multi.seoulsoul.event.model.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Override
    public void eventInsertForm(EventDTO eventDTO) throws Exception {
        eventDAO.insertEvent(eventDTO);
        System.out.println("EventDAO.insertEvent called with: " + eventDTO);
    }
}