package com.multi.seoulsoul.event.controller;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.multi.seoulsoul.event.model.dto.EventDTO;
import com.multi.seoulsoul.event.service.EventService;

@Controller
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventService eventService;
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    if (text != null && !text.isEmpty()) {
                        setValue(new Timestamp(dateFormat.parse(text).getTime()));
                    } else {
                        setValue(null);
                    }
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });
    }

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
            model.addAttribute("msg", "이벤트 상세 정보를 가져오는 중 오류가 발생했습니다.");
            return "redirect:/WEB-INF/common/errorPage.jsp";
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
    @PostMapping("/updateEvent")
    public String updateEvent(
            HttpServletRequest request,
            @RequestParam("eventNo") int eventNo,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("address") String address,
            @RequestParam("startDate") Timestamp startDate,
            @RequestParam("endDate") Timestamp endDate,
            @RequestParam("image") MultipartFile image,
            RedirectAttributes redirectAttributes) {
        try {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setEventNo(eventNo);
            eventDTO.setTitle(title);
            eventDTO.setContent(content);
            eventDTO.setAddress(address);
            eventDTO.setStartDate(startDate);
            eventDTO.setEndDate(endDate);

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

                // 기존 이미지 파일 삭제 (선택사항)
                EventDTO existingEvent = eventService.selectEventByNo(eventNo);
                if (existingEvent.getImagePath() != null) {
                    File oldFile = new File(uploadDir + "/" + existingEvent.getImagePath());
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                }

                // 이미지 경로 업데이트
                Map<String, Object> fileParams = new HashMap<>();
                fileParams.put("eventNo", eventNo);
                fileParams.put("originalName", originalFilename);
                fileParams.put("savedName", savedFilename);
                eventService.updateEventFile(fileParams);
            }

            // 이벤트 업데이트 서비스 호출
            eventService.updateEvent(eventDTO);

            redirectAttributes.addFlashAttribute("successMessage", "이벤트가 성공적으로 수정되었습니다.");
            return "redirect:/event/eventMain";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "이벤트 수정에 실패했습니다.");
            return "redirect:/event/editEvent?eventNo=" + eventNo;
        }
    }
 
    
    @GetMapping("/editEvent")
    public String editEvent(@RequestParam("eventNo") int eventNo, Model model) {
        try {
            EventDTO event = eventService.selectEventByNo(eventNo);
            if (event == null) {
                return "error/404"; // 이벤트가 없을 경우 404 페이지로 리디렉션
            }
            model.addAttribute("event", event);
        } catch (Exception e) {
            e.printStackTrace();
            return "error/errorPage"; // 예외 발생 시 에러 페이지로 리디렉션
        }
        return "event/editEvent";
    }
    @PostMapping("/deleteEvent")
    @Transactional
    public String deleteEvent(@RequestParam("eventNo") int eventNo, RedirectAttributes redirectAttributes) {
        try {
            // 이벤트 삭제 서비스 호출
            eventService.deleteEvent(eventNo);
            redirectAttributes.addFlashAttribute("message", "이벤트가 성공적으로 삭제되었습니다.");
            return "redirect:/event/eventMain";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "이벤트 삭제에 실패했습니다.");
            return "redirect:/WEB-INF/common/errorPage.jsp";
        }
    }
    

}

