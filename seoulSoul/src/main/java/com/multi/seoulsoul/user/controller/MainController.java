package com.multi.seoulsoul.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main")
    public String mainPage() {
        return "main";
    }

}
