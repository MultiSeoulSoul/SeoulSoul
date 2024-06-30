package com.multi.seoulsoul.user.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.seoulsoul.user.model.dto.CustomUserDetails;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.service.UserService;

@Controller
public class MainController {
	
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public MainController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@GetMapping("/main")
    public void mainPage() {
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
