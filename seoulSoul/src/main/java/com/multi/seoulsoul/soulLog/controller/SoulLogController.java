package com.multi.seoulsoul.soulLog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.seoulsoul.soulLog.model.dto.PageDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;
import com.multi.seoulsoul.soulLog.service.SoulLogService;


@Controller
@RequestMapping("/soulLog")
public class SoulLogController {
	
	
	private final SoulLogService soulLogService;
	
	public SoulLogController(SoulLogService soulLogService) {
		this.soulLogService = soulLogService;
	}
	
    
	// 소울로그 메인 리스트 페이지로 이동
	@RequestMapping("/soulLogMain")
	public String soulLogMain(PageDTO pageDTO, Model model) {
		
		// PageDTO의 메소드로 start end를 set한다.
		// 메인 리스트 이동 때는 page=1 이므로 start=1, end=15
		pageDTO.setStartAndStartIndex(pageDTO.getPage());
		
		System.out.println("시작 페이지는 >>>> " + pageDTO.getStart());
		
		try {
			
			List<SoulLogDTO> soulLogList = soulLogService.selectSoulLogList(pageDTO);
			
			System.out.println("가져온 리스트 >>>> " + soulLogList);
			
			model.addAttribute("soulLogList", soulLogList);
			
			return "soulLog/soulLogMain";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "common/errorPage";
			
		}
		
		
	}
	
	
	
	
}
