package com.multi.seoulsoul.event.service;

import com.multi.seoulsoul.event.model.dto.EventDTO;

public interface EventService {
    void eventInsertForm(EventDTO eventDTO) throws Exception;
}