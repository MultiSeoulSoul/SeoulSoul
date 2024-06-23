package com.multi.seoulsoul.rec.controller;

import com.multi.seoulsoul.rec.model.dto.RecDTO;
import com.multi.seoulsoul.rec.service.RecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("rec")
public class RecController {

    @Autowired
    private RecService recService;

    @GetMapping("recMain")
    public void recMain() {
        
    }

    @GetMapping("recInsertForm")
    public void recInsertForm() {
       
    }

    @PostMapping("recInsertForm")
    public ModelAndView insertRecommendation(@RequestParam("title") String title,
                                             @RequestParam("content") String content) {
        RecDTO recDTO = new RecDTO();
        recDTO.setTitle(title);
        recDTO.setContent(content);
        recDTO.setViews(0); 

        try {
            recService.recInsertForm(recDTO);
        } catch (Exception e) {
            e.printStackTrace(); 
            
            return new ModelAndView("redirect:/rec/recMain");
        }

        return new ModelAndView("redirect:/rec/recMain");
    }
}