package com.multi.seoulsoul.event.controller;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.multi.seoulsoul.event.model.dto.EventDTO;
import com.multi.seoulsoul.event.service.EventService;

@Controller
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/eventMain")
    public String eventMain(Model model) {
        try {
            List<EventDTO> eventList = eventService.selectAllEvents();
            model.addAttribute("eventList", eventList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "event/eventMain";
    }

    @GetMapping("eventInsertForm")
    public String eventInsertForm() {
        return "event/eventInsertForm";
    }

    @PostMapping("/eventInsertForm")
    public ModelAndView insertEvent(
            HttpServletRequest request,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("address") String address,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam("image") MultipartFile image) throws Exception {

        EventDTO eventDTO = new EventDTO();
        eventDTO.setTitle(title);
        eventDTO.setContent(content);
        eventDTO.setAddress(address);
        eventDTO.setStartDate(Timestamp.valueOf(startDate));
        eventDTO.setEndDate(Timestamp.valueOf(endDate));
        eventDTO.setViews(0);

        // 이벤트 삽입 서비스 호출
        eventService.eventInsertForm(eventDTO);

        // 파일 업로드 처리 및 이미지 경로 설정
        if (!image.isEmpty()) {
            String uploadDir = request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originalFilename = image.getOriginalFilename();
            String savedFilename = UUID.randomUUID().toString() + "_" + originalFilename;
            File saveFile = new File(uploadDir + "/" + savedFilename);
            image.transferTo(saveFile);

            // 파일 정보를 EVENT_FILES 테이블에 저장
            Map<String, Object> fileParams = new HashMap<>();
            fileParams.put("eventNo", eventDTO.getEventNo());
            fileParams.put("originalName", originalFilename);
            fileParams.put("savedName", savedFilename);
            eventService.saveFile(fileParams);
        }

        return new ModelAndView("redirect:/event/eventMain");
    }

    @GetMapping("/eventDetail")
    public String eventDetail(@RequestParam("eventNo") int eventNo, Model model) {
        try {
            // 이벤트 조회수 증가
            eventService.incrementViews(eventNo);

            // 이벤트 상세 정보 가져오기
            EventDTO eventDTO = eventService.selectEventByNo(eventNo);
            model.addAttribute("event", eventDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "event/eventDetail";
    }
    @PostMapping("/insertEvent")
    public String insertEvent(@RequestParam("title") String title, @RequestParam("address") String address, Model model) {
        // 데이터베이스에 이벤트 저장 (로직 생략)
        
        // 모델에 저장한 데이터 전달
        model.addAttribute("title", title);
        model.addAttribute("address", address);
        
        return "redirect:/eventDetail";
    }
}