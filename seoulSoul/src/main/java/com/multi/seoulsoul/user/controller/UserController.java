package com.multi.seoulsoul.user.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.seoulsoul.soulLog.model.dto.SLBoardDTO;
import com.multi.seoulsoul.soulLog.model.dto.SLReplyDTO;
import com.multi.seoulsoul.user.model.dto.CustomUserDetails;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;
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
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}
	
	@GetMapping("/userMain")
	public void userMain() {
	}
	
	@GetMapping("/main")
	public void Main() {
	}
	
	@GetMapping("/userUpdateForm")
	public void userUpdateForm() {
	}
	
	@GetMapping("/userDelete")
	public void Delete() {
	}

	@PostMapping("/userUpdate")
	public String userUpdate(@AuthenticationPrincipal Principal principal, UserDTO u, HttpSession session) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
		
		u.setUserNo(userDetails.getUserNo());
		
		try {
			userService.userUpdate(u);
		} catch (Exception e) {
			session.setAttribute("msg", "회원정보 변경에 실패하였습니다");
		}
		session.setAttribute("msg", "회원정보 변경에 설공하였습니다");
		return "/user/userMain";
	}
	
	@PostMapping("/userPwUpdate")
	public String userPwUpdate(@AuthenticationPrincipal Principal principal, UserDTO u, HttpSession session) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
		
		u.setUserNo(userDetails.getUserNo());
		
		try {
			userService.userPwUpdate(u);
		} catch (Exception e) {
			session.setAttribute("msg", "비밀번호 변경에 실패하였습니다");
		}
		session.setAttribute("msg", "비밀번호 변경에 설공하였습니다");
		return "/user/userUpdateForm";
	}
	
	@PostMapping("/userDelete")
	public String userDelete(@AuthenticationPrincipal Principal principal) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
		
	    try {
			userService.userDelete(userDetails.getUserNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/user/logout";
	}
	
	@GetMapping("/checkDuplicateId")
    @ResponseBody
    public boolean checkDuplicateId(String userId) {
        return userService.isUserIdAvailable(userId);
    }
	
	@GetMapping("/checkDuplicateNickname")
    @ResponseBody
    public boolean checkDuplicateNickname(String nickname) {
        return userService.isUserNicknameAvailable(nickname);
    }
	
	@PostMapping("/checkCurrentPassword")
	@ResponseBody
	public boolean checkCurrentPassword(@AuthenticationPrincipal Principal principal, String currentPassword) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
	    
	    return bCryptPasswordEncoder.matches(currentPassword, userDetails.getPassword());
	}
	
	@GetMapping("/SLBoardPage")
	@ResponseBody
	public Map<String, Object> selectSLBoardPage(@AuthenticationPrincipal Principal principal, UserPageDTO up) {
	    // Principal 객체가 UsernamePasswordAuthenticationToken 으로 래핑되어있음
	    // -> Principal을 UsernamePasswordAuthenticationToken 으로 캐스팅한 후
	    // -> 다시 CustomUserDetails 으로 캐스팅하면 사용자 정보를 추출할 수 있음
	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();

	    up.setUserNo(userDetails.getUserNo());
	    up.setStartEnd(up.getPage());
	        
	    List<SLBoardDTO> slBoard = userService.selectSLBoardPage(up);
	    
	    System.out.println("slBoard:"+slBoard);
	    
	    int count = slBoard.get(0).getTotalCount();
	    int pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;

	    Map<String, Object> response = new HashMap<>();
	    response.put("slBoard", slBoard);
	    response.put("pages", pages);
	    
	    System.out.println("response" + response);
	    
	    return response;
	}
	
	@GetMapping("/SLReplyPage")
	@ResponseBody
	public Map<String, Object> selectSLBoardReplyPage(@AuthenticationPrincipal Principal principal, UserPageDTO up) {
	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();

	    up.setUserNo(userDetails.getUserNo());
	    up.setStartEnd(up.getPage());
	        
	    List<SLReplyDTO> slReply = userService.selectSLReplyPage(up);
	    
	    System.out.println("slReply:"+slReply);
	    
	    int count = slReply.get(0).getCount();
	    int pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;

	    Map<String, Object> response = new HashMap<>();
	    response.put("slReply", slReply);
	    response.put("pages", pages);
	    
	    System.out.println("response" + response);
	    
	    return response;
	}

}
