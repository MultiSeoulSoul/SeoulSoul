package com.multi.seoulsoul.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.service.UserService;

@SessionAttributes("loginUser")
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
    public String showLoginPage() {
        return "user/login";
    }
  
	@PostMapping("/login")
	public String loginMember(@ModelAttribute UserDTO u, Model model) {

		System.out.println("insert ==> " + u);

		try {
			UserDTO userDTO = userService.loginUser(u);
			model.addAttribute("loginUser", userDTO);
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}

}
