package com.multi.seoulsoul.user.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.service.CustomUserDetailsService;
import com.multi.seoulsoul.user.service.UserService;

@Controller
@RequestMapping("/kakao")
@PropertySource("classpath:db.properties")
public class KakaoController {
	
    private final CustomUserDetailsService customUserDetailsService;
	private final UserService userService;
	
	@Autowired
	public KakaoController(
			CustomUserDetailsService customUserDetailsService,
			UserService userService) {
		this.customUserDetailsService = customUserDetailsService;
		this.userService = userService;
	}

	@Autowired
	ApplicationContext ctx;
	
	// API키 가져오기
	@GetMapping("/getKakaoApiKey")
    @ResponseBody
    public String getKakaoApiKey() {
        Environment env = ctx.getEnvironment();
        return env.getProperty("kakao.api.key");
    }
	
	// 카카오Id 중복조회
	@PostMapping("/idDuplicateCheck")
    @ResponseBody
    public ResponseEntity<?> idDuplicateCheck(String userId) {
        try {
            customUserDetailsService.loadUserByUsername(userId);
            return ResponseEntity.ok(Collections.singletonMap("idExists", true));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.ok(Collections.singletonMap("idExists", false));
        }
    }
	
	// 카카오 회원 가입
	@PostMapping("/kakaoSignUp")
    @ResponseBody
    public ResponseEntity<?> kakaoSignUp(UserDTO u) {
		String kakaoPw = "kakaoPw";
		u.setUserPw(kakaoPw);
		
		try {
			userService.joinUser(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return ResponseEntity.ok(Collections.singletonMap("success", true));
    }
}
