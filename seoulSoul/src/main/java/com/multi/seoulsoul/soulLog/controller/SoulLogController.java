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
		
		pageDTO.setStartAndStartIndex(pageDTO.getPage());
		
		System.out.println("시작 페이지는 >>>> " + pageDTO.getStart());
		
		try {
			
			List<SoulLogDTO> soulLogList = soulLogService.selectSoulLogList(pageDTO);
			
			System.out.println("가져온 리스트 >>>> " + soulLogList);
			
			model.addAttribute("soulLogList", soulLogList);
			
			int SoullogCount = soulLogService.selectSoulLogCount();
			
			// 글 수를 토대로 전체 페이지 수를 구한다.
			// 글 수가 15의 배수면 +1을 하지 않는다.
			int pages = SoullogCount / 15;

			// 글 수가 15의 배수가 아니면 +1을 한다.
			if (SoullogCount % 15 != 0) {
				pages += 1;
			}

			model.addAttribute("pages", pages);
			
			return "soulLog/soulLogMain";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "common/errorPage";
			
		}
		
		
	}
	
	
	// 페이징 버튼 눌렀을 때 소울로그 리스트를 조회 (ajax)
	@RequestMapping("/soulLogList")
	public String soulLogList(PageDTO pageDTO, Model model) {
		
		pageDTO.setStartAndStartIndex(pageDTO.getPage());
		
		System.out.println("시작 페이지는 >>>> " + pageDTO.getStart());
		
		try {
			
			List<SoulLogDTO> soulLogList = soulLogService.selectSoulLogList(pageDTO);
			
			System.out.println("가져온 리스트 >>>> " + soulLogList);
			
			model.addAttribute("soulLogList", soulLogList);
			
			return "soulLog/soulLogList";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "common/errorPage";
			
		}
		
		
	}
	
	
	
}
