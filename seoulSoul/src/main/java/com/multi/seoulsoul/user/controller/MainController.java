package com.multi.seoulsoul.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.service.UserService;

@Controller
public class MainController {
	
	private final UserService userService;

	@Autowired
	public MainController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/main")
    public void mainPage() {
    }
	
	@GetMapping("/join")
	public String joinPage() {
		return "/user/join";
	}
	
	@PostMapping("/join")
	public String joinUser(UserDTO u, HttpSession session) throws Exception {
		userService.joinUser(u);
		session.setAttribute("msg", "회원가입성공");
		return "/user/login";
	}
	
	
}
