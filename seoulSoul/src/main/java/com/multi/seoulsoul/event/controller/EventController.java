package com.multi.seoulsoul.event.controller;

import com.multi.seoulsoul.event.model.dto.EventDTO;
import com.multi.seoulsoul.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("eventMain")
    public void eventMain() {
        
    }

    @GetMapping("eventInsertForm")
    public void eventInsertForm() {
      
    }

    @PostMapping("eventInsertForm")
    public ModelAndView insertEvent(@RequestParam("title") String title,
                                    @RequestParam("content") String content,
                                    @RequestParam("startDate") String startDateStr,
                                    @RequestParam("endDate") String endDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Timestamp startDate;
        Timestamp endDate;
        try {
            startDate = new Timestamp(dateFormat.parse(startDateStr).getTime());
            endDate = new Timestamp(dateFormat.parse(endDateStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/event/eventInsertForm?error=invalidDate");
        }

        EventDTO eventDTO = new EventDTO();
        eventDTO.setTitle(title);
        eventDTO.setContent(content);
        eventDTO.setStartDate(startDate);
        eventDTO.setEndDate(endDate);
        eventDTO.setViews(0); 

        try {
            eventService.eventInsertForm(eventDTO);
            System.out.println("Event successfully inserted: " + eventDTO);
        } catch (Exception e) {
            e.printStackTrace(); 
            return new ModelAndView("redirect:/event/eventMain?error=databaseError"); 
        }

        return new ModelAndView("redirect:/event/eventMain");
    }
}