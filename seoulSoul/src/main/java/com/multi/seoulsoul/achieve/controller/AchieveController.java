package com.multi.seoulsoul.achieve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String adminMainPage(Model model) {
		System.out.println("관리자 메인 페이지 호출 성공.");
		List<AchieveDTO> achieveLocaList = achieveService.achieveLocaList();
		List<AchieveDTO> achieveCateList = achieveService.achieveCateList();
        
        model.addAttribute("achieveLocaList", achieveLocaList);
        model.addAttribute("achieveCateList", achieveCateList);
        
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
		
		int result = 0;
		
		if (achieveDTO.getLocationCode() != 0) {
			result = achieveService.insertAchieveLoca(achieveDTO);
		} else {
			result = achieveService.insertAchieveCate(achieveDTO);
		}
		
		if (result > 0) {
			System.out.println("업적 생성 성공.");
		} else {
			System.out.println("업적 생성 실패.");
		}
		
		return "redirect:/admin/adminMain";
	}
	
	@RequestMapping("/delete")
	public String achieveDelete(AchieveDTO achieveDTO) {
		System.out.println("Request >> achieveDelete.");
		System.out.println("Request >> " + achieveDTO);
		
		int result = 0;
		
		if (achieveDTO.getLocationCode() != 0) {
			result = achieveService.deleteAchieveLoca(achieveDTO.getAchNo());
		} else {
			result = achieveService.deleteAchieveCate(achieveDTO.getAchNo());
		}
		
		if (result > 0) {
			System.out.println("업적 생성 성공.");
		} else {
			System.out.println("업적 생성 실패.");
		}
		
		return "redirect:/admin/adminMain";
	}

}
