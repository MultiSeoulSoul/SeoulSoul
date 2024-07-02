package com.multi.seoulsoul.user.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.StatsDTO;
import com.multi.seoulsoul.soulLog.service.SoulLogService;
import com.multi.seoulsoul.user.model.dto.CustomUserDetails;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.service.UserService;

@Controller
public class MainController {
	
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final SoulLogService soulLogService;
	
	@Autowired
	public MainController(
			UserService userService,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			SoulLogService soulLogService
			) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
	
	@GetMapping("/join")
	public String joinPage() {
		return "/user/join";
	}
	
	@PostMapping("/join")
	public String joinUser(UserDTO u) throws Exception {
		userService.joinUser(u);
		return "/user/login";
	}
	
	@RequestMapping("/errorPage")
	public String errorPage() {
		return "/common/errorPage";
	}
	
	// 아이디 중복조회
	@GetMapping("/checkDuplicateId")
    @ResponseBody
    public boolean checkDuplicateId(String userId) {
        return userService.isUserIdAvailable(userId);
    }
	
	// 닉네임 중복조회
	@GetMapping("/checkDuplicateNickname")
    @ResponseBody
    public boolean checkDuplicateNickname(String nickname) {
        return userService.isUserNicknameAvailable(nickname);
    }
	
	// 현재 비밀번호 조회
	@PostMapping("/checkCurrentPassword")
	@ResponseBody
	public boolean checkCurrentPassword(@AuthenticationPrincipal Principal principal, String currentPassword) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
	    
	    return bCryptPasswordEncoder.matches(currentPassword, userDetails.getPassword());
	}
}
