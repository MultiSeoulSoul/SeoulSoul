package com.multi.seoulsoul.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("loginUser")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}
	
	@GetMapping("/join")
	public void joinPage() {
	}
	
	@GetMapping("/userMain")
	public void userMain() {
	}
	
	@GetMapping("/main")
	public void Main() {}

	
	
	@GetMapping("/checkDuplicateId")
    @ResponseBody
    public boolean checkDuplicateId(@RequestParam("userId") String userId) {
        return userService.isUserIdAvailable(userId);
    }
	
	@GetMapping("/checkDuplicateNickname")
    @ResponseBody
    public boolean checkDuplicateNickname(@RequestParam("nickname") String nickname) {
        return userService.isUserNicknameAvailable(nickname);
    }
	
	@PostMapping("/join")
	public String joinUser(UserDTO u, HttpSession session) throws Exception {
		userService.joinUser(u);
		session.setAttribute("msg", "회원가입성공");
		return "/user/login";
	}
	
//	@PostMapping("/login")
//	public String loginUser(UserDTO u, HttpSession session) {
//	    try {
//	    	UserDTO userDTO = userService.loginUser(u);
//	        session.setAttribute("loginUser", userDTO);
//	        return "redirect:/";
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return "redirect:/";
//	    }
//	}
//	
//	@PostMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}

}
