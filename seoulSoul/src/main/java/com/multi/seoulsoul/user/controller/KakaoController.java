package com.multi.seoulsoul.user.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.seoulsoul.user.model.dto.CustomUserDetails;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.service.CustomUserDetailsService;
import com.multi.seoulsoul.user.service.UserService;

@Controller
@RequestMapping("/kakao")
public class KakaoController {
	
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/idDuplicateCheck")
    @ResponseBody
    public ResponseEntity<?> idDuplicateCheck(String userId) {
		System.out.println("id중복검사 호출");
        try {
            CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userId);
            System.out.println("customUserDetails:"+customUserDetails);
            return ResponseEntity.ok(Collections.singletonMap("idExists", true));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.ok(Collections.singletonMap("idExists", false));
        }
    }
	
	@PostMapping("/kakaoSignUp")
    @ResponseBody
    public ResponseEntity<?> kakaoSignUp(UserDTO u) {
		
		String kakaoPw = "kakaoPw";
		u.setUserPw(kakaoPw);
		
		System.out.println("DTO:" + u);
		
		try {
			userService.joinUser(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return ResponseEntity.ok(Collections.singletonMap("success", true));
    }

	@PostMapping("/kakaoLogin")
	@ResponseBody
	public void kakaoLogin() {}
}
