package com.multi.seoulsoul.soulLog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.StatsDTO;
import com.multi.seoulsoul.soulLog.service.SoulLogService;

@Controller
@RequestMapping("/main")
public class MainController {

	private final SoulLogService soulLogService;

	@Autowired
	public MainController(SoulLogService soulLogService) {
		this.soulLogService = soulLogService;
	}
	
	@GetMapping("/main")
    public String mainPage(Model model) {
		
		int userNo = 1; // 로그인된 유저의 no로 바꿔야 함
		
		try {
			
			List<StatsDTO> userStats = soulLogService.selectStats(userNo);
			
			for(int i = 0; i < userStats.size(); i++) {
				int exp = userStats.get(i).getSoulLogCount() * 100 +
						  userStats.get(i).getLikeCount() + 
						  userStats.get(i).getReplyCount() * 3;
				userStats.get(i).setExp(exp);
			}
			
			model.addAttribute("userStats", userStats);
			
			List<LocationDTO> locationList = soulLogService.selectLocationList();
			
			model.addAttribute("locationList", locationList);
			
			return "main";
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "소울맴 조회 과정에서 문제가 발생했습니다.");
			
			return "common/errorPage";
			
		}
		
    }
	
}
