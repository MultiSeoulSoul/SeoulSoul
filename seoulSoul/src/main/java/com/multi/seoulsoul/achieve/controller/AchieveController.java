package com.multi.seoulsoul.achieve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.seoulsoul.achieve.model.dto.AchieveDTO;
import com.multi.seoulsoul.achieve.service.AchieveService;

@Controller
@RequestMapping("/admin")
public class AchieveController {
	
	private final AchieveService achieveService;
	@Autowired
	public AchieveController(AchieveService achieveService) {
		this.achieveService = achieveService;
	}
	
	@GetMapping("/adminMain")
	public String adminMainPage() {
		return "achieve/adminMain";
	}
	
	@GetMapping("/achieveInsertForm")
	public String achieveInsertForm() {
		return "achieve/achieveInsertForm";
	}
	
	@PostMapping("/achieveInsertForm")
	public String achieveInsertForm(AchieveDTO achieveDTO) {
		System.out.println("Post >> achieveInsertForm.");
		System.out.println("Post >> " + achieveDTO);
		
		int result = achieveService.insertAchieveLoca(achieveDTO);
		
		if (result > 0) {
			System.out.println("업적 생성 성공.");
		} else {
			System.out.println("업적 생성 실패.");
		}
		
		return "/achieve/adminMain";
	}

}
