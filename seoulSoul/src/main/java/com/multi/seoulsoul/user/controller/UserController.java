package com.multi.seoulsoul.user.controller;

import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.soulLog.model.dto.SLBoardDTO;
import com.multi.seoulsoul.soulLog.model.dto.SLReplyDTO;
import com.multi.seoulsoul.user.model.dto.CustomUserDetails;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;
import com.multi.seoulsoul.user.model.dto.UserProfileDTO;
import com.multi.seoulsoul.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	// user 관련 항목을 업데이트 할 때 userService.updateCustomUserDetails(userDetails.getUsername()); 반드시 사용
	// 블랙리스트에도 넣어야 할듯
	
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
		
		userService.updateCustomUserDetails(userDetails.getUsername());
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
		
		userService.updateCustomUserDetails(userDetails.getUsername());
		return "/user/userUpdateForm";
	}
	
	@PostMapping("/userDelete")
	public String userDelete(@AuthenticationPrincipal Principal principal, HttpServletRequest request, HttpServletResponse response) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
		
	    try {
			userService.userDelete(userDetails.getUserNo());
			new SecurityContextLogoutHandler().logout(request, response, authenticationToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/user/login";
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
	
	@PostMapping("/updateProfile")
	@ResponseBody
	public Map<String, Object> updateProfile(@AuthenticationPrincipal Principal principal, UserProfileDTO up, HttpServletRequest request, MultipartFile profileImage) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
	    
	    up.setUserNo(userDetails.getUserNo());
		
		Map<String, Object> response = new HashMap<>();
		
		System.out.println("받은 profileImage:" + profileImage);
		System.out.println("받은 UserProfileDTO:" + up);
		
		if (profileImage != null && !profileImage.isEmpty()) {
			String root = request.getSession().getServletContext().getRealPath("resources");
			
			System.out.println("root : " + root);
			
			String filePath = root + "\\uploadFiles";
			File mkdir = new File(filePath);
			if (!mkdir.exists()) {
				mkdir.mkdirs();
			}
			
			String originFileName = profileImage.getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			up.setProfilePicName(savedName);
			
			try {
				profileImage.transferTo(new File(filePath + "\\" + savedName));
				
			} catch (Exception e) {
				e.printStackTrace();
				/* 실패시 파일 삭제 */
				new File(filePath + "\\" + savedName).delete();
			}
		}
		
		try {
			userService.updateProfile(up);
			response.put("nickname", up.getNickname());
			response.put("ProfileContent", up.getProfileContent());
			response.put("ProfilePicName", up.getProfilePicName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userService.updateCustomUserDetails(userDetails.getUsername());
		return response;
	}
	
}
