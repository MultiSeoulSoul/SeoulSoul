package com.multi.seoulsoul.achieve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;
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
		List<AchLocaDTO> achieveLocaList = achieveService.achieveLocaList();
		List<AchCateDTO> achieveCateList = achieveService.achieveCateList();
        
        model.addAttribute("achieveLocaList", achieveLocaList);
        model.addAttribute("achieveCateList", achieveCateList);
        
		return "achieve/adminMain";
	}
	
	@GetMapping("/achieveInsertForm")
	public String achieveInsertForm() {
		return "achieve/achieveInsertForm";
	}
	
	@PostMapping("/achieveInsertForm")
	public String achieveInsertForm(AchLocaDTO achLocaDTO, AchCateDTO achCateDTO) {
		System.out.println("Post >> achieveInsertForm.");
		System.out.println("Post >> " + achLocaDTO);
		
		int result = 0;
		
		if (achLocaDTO.getLocationCode() != 0) {
			result = achieveService.insertAchieveLoca(achLocaDTO);
		} else {
			result = achieveService.insertAchieveCate(achCateDTO);
		}
		
		if (result > 0) {
			System.out.println("업적 생성 성공.");
		} else {
			System.out.println("업적 생성 실패.");
		}
		
		return "redirect:/admin/adminMain";
	}
	
	@GetMapping("achieveUpdateForm")
	public String achieveUpdateForm() {
		return "achieve/achieveUpdateForm";
	}
	
	@GetMapping("/deleteLoca")
	public String achieveDeleteLoca(AchLocaDTO achLocaDTO) {
		System.out.println("Request >> achieveDelete.");
		System.out.println("Request >> " + achLocaDTO);
		
		int result = achieveService.deleteAchieveLoca(achLocaDTO.getAchNo());
		
		if (result > 0) {
			System.out.println("업적 삭제 성공.");
		} else {
			System.out.println("업적 삭제 실패.");
		}
		
		return "redirect:/admin/adminMain";
	}
	
	@GetMapping("/deleteCate")
	public String achieveDeleteCate(AchLocaDTO achLocaDTO) {
		System.out.println("Request >> achieveDelete.");
		System.out.println("Request >> " + achLocaDTO);
		
		int result = achieveService.deleteAchieveLoca(achLocaDTO.getAchNo());
		
		if (result > 0) {
			System.out.println("업적 삭제 성공.");
		} else {
			System.out.println("업적 삭제 실패.");
		}
		
		return "redirect:/admin/adminMain";
	}

}
