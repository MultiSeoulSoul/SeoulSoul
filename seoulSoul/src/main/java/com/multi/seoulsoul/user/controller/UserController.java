package com.multi.seoulsoul.user.controller;

import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.user.model.dto.CustomUserDetails;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;
import com.multi.seoulsoul.user.model.dto.UserProfileDTO;
import com.multi.seoulsoul.user.service.UserService;
import com.multi.seoulsoul.user.tempDTO.CsQuestionDTO;
import com.multi.seoulsoul.user.tempDTO.LikesDTO;
import com.multi.seoulsoul.user.tempDTO.RecHeartBtnDTO;
import com.multi.seoulsoul.user.tempDTO.ReplyDTO;
import com.multi.seoulsoul.user.tempDTO.ReportDTO;
import com.multi.seoulsoul.user.tempDTO.SLBoardDTO;
import com.multi.seoulsoul.user.tempDTO.SLReplyDTO;

@Controller
@RequestMapping("/user")
public class UserController {

	// user 관련 항목을 업데이트 할 때 userService.updateCustomUserDetails(userDetails.getUsername()); 반드시 사용
	// 블랙리스트에도 넣어야 할듯
	
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserController(
			UserService userService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@GetMapping("/login")
	public void loginPage() {
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

	// 회원정보 업데이트
	@PostMapping("/userUpdate")
	public String userUpdate(@AuthenticationPrincipal Principal principal, UserDTO u, Model model) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
		
		u.setUserNo(userDetails.getUserNo());
		
		try {
			userService.userUpdate(u);
		} catch (Exception e) {
			model.addAttribute("msg", "회원정보 변경에 실패하였습니다.");
			return "common/errorPage";
		}
		
		userService.updateCustomUserDetails(userDetails.getUsername());
		return "/user/userMain";
	}
	
	// 비밀번호 업데이트
	@PostMapping("/userPwUpdate")
	public String userPwUpdate(@AuthenticationPrincipal Principal principal, UserDTO u, Model model) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
		
		u.setUserNo(userDetails.getUserNo());
		
		try {
			userService.userPwUpdate(u);
		} catch (Exception e) {
			model.addAttribute("msg", "비밀번호 변경에 실패하였습니다.");
			return "common/errorPage";
		}
		
		userService.updateCustomUserDetails(userDetails.getUsername());
		return "/user/userUpdateForm";
	}
	
	// 프로필 업데이트
	@PostMapping("/updateProfile")
	@ResponseBody
	public Map<String, Object> updateProfile(
			@AuthenticationPrincipal Principal principal,
			UserProfileDTO up,
			HttpServletRequest request,
			MultipartFile profileImage) {
		
		System.out.println("프로필수정 받은내용: " + up + "프로필사진: " + profileImage);
		
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
	    
	    up.setUserNo(userDetails.getUserNo());
		
		Map<String, Object> response = new HashMap<>();
		
		if (profileImage != null && !profileImage.isEmpty()) {
			String root = request.getSession().getServletContext().getRealPath("resources");
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
				response.put("ProfilePicName", up.getProfilePicName());
			} catch (Exception e) {
				e.printStackTrace();
				// 실패시 파일 삭제
				new File(filePath + "\\" + savedName).delete();
				response.put("error", "파일 저장 중 오류 발생");
	            return response;
			}
		}
		
		try {
			userService.updateProfile(up);
			response.put("ProfileContent", up.getProfileContent());
			
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "프로필 업데이트 중 오류 발생");
			return response;
		}
		
		System.out.println("프로필 업데이트 응답: " + response);
		
		userService.updateCustomUserDetails(userDetails.getUsername());
		return response;
	}
	
	// 회원 탈퇴
	@PostMapping("/userDelete")
	public String userDelete(
			@AuthenticationPrincipal Principal principal,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model) {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
		
	    try {
			userService.userDelete(userDetails.getUserNo());
			new SecurityContextLogoutHandler().logout(request, response, authenticationToken);
		} catch (Exception e) {
			model.addAttribute("msg", "회원 탈퇴에 실패하였습니다.");
			return "common/errorPage";
		}
		
		return "redirect:/user/login";
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
	
	@GetMapping("/{boardType}Page")
	@ResponseBody
	public Map<String, Object> getBoardPage(@PathVariable String boardType, @AuthenticationPrincipal Principal principal, UserPageDTO up) {
	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();

	    up.setUserNo(userDetails.getUserNo());
	    up.setStartEnd(up.getPage());
	    
	    Map<String, Object> response = new HashMap<>();
	    List<?> dataList;
	    int count, pages;

	    switch (boardType) {
	        case "soul-log":
	            dataList = userService.selectSLBoardPage(up);
	            count = ((SLBoardDTO) dataList.get(0)).getTotalCount();
	            response.put("slBoard", dataList);
	            break;
	        case "soul-log-reply":
	            dataList = userService.selectSLReplyPage(up);
	            count = ((SLReplyDTO) dataList.get(0)).getTotalCount();
	            response.put("slReply", dataList);
	            break;
	        case "event-reply":
	            dataList = userService.selectEventReplyPage(up);
	            count = ((ReplyDTO) dataList.get(0)).getTotalCount();
	            response.put("eventReply", dataList);
	            break;
	        case "likes":
	            dataList = userService.selectLikesPage(up);
	            count = ((LikesDTO) dataList.get(0)).getTotalCount();
	            response.put("likes", dataList);
	            break;
	        case "heart-btn":
	            dataList = userService.selectHeartBtnPage(up);
	            count = ((RecHeartBtnDTO) dataList.get(0)).getTotalCount();
	            response.put("heartBtn", dataList);
	            break;
	        case "cs-question":
	            dataList = userService.selectCsQuestionPage(up);
	            count = ((CsQuestionDTO) dataList.get(0)).getTotalCount();
	            response.put("csQuestion", dataList);
	            break;
	        case "report":
	            dataList = userService.selectReportPage(up);
	            count = ((ReportDTO) dataList.get(0)).getTotalCount();
	            response.put("report", dataList);
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid board type: " + boardType);
	    }

	    pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;
	    response.put("pages", pages);

	    return response;
	}

//	
//	
//	// 소울로그 조회
//	@GetMapping("/SLBoardPage")
//	@ResponseBody
//	public Map<String, Object> selectSLBoardPage(@AuthenticationPrincipal Principal principal, UserPageDTO up) {
//	    // Principal 객체가 UsernamePasswordAuthenticationToken 으로 래핑되어있음
//	    // -> Principal을 UsernamePasswordAuthenticationToken 으로 캐스팅한 후
//	    // -> 다시 CustomUserDetails 으로 캐스팅하면 사용자 정보를 추출할 수 있음
//	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
//	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
//
//	    up.setUserNo(userDetails.getUserNo());
//	    up.setStartEnd(up.getPage());
//	        
//	    List<SLBoardDTO> slBoard = userService.selectSLBoardPage(up);
//	    
//	    int count = slBoard.get(0).getTotalCount();
//	    int pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;
//
//	    Map<String, Object> response = new HashMap<>();
//	    response.put("slBoard", slBoard);
//	    response.put("pages", pages);
//	    
//	    return response;
//	}
//	
//	// 소울로그 댓글 조회
//	@GetMapping("/SLReplyPage")
//	@ResponseBody
//	public Map<String, Object> selectSLBoardReplyPage(@AuthenticationPrincipal Principal principal, UserPageDTO up) {
//	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
//	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
//
//	    up.setUserNo(userDetails.getUserNo());
//	    up.setStartEnd(up.getPage());
//	        
//	    List<SLReplyDTO> slReply = userService.selectSLReplyPage(up);
//	    
//	    int count = slReply.get(0).getTotalCount();
//	    int pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;
//
//	    Map<String, Object> response = new HashMap<>();
//	    response.put("slReply", slReply);
//	    response.put("pages", pages);
//	    
//	    return response;
//	}
//	
//	// 이벤트 댓글 조회 이벤트제목, 댓글내용
//	@GetMapping("/EventReplyPage")
//	@ResponseBody
//	public Map<String, Object> selectEventReplyPage(@AuthenticationPrincipal Principal principal, UserPageDTO up) {
//	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
//	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
//
//	    up.setUserNo(userDetails.getUserNo());
//	    up.setStartEnd(up.getPage());
//	    
//	    List<SLReplyDTO> eventReply = userService.selectEventReplyPage(up);
//	    
//	    int count = eventReply.get(0).getTotalCount();
//	    int pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;
//
//	    Map<String, Object> response = new HashMap<>();
//	    response.put("eventReply", eventReply);
//	    response.put("pages", pages);
//	    
//	    return response;
//	}
//	
//	// 소울로그 좋아요 조회 지역이름, 카테고리이름, 소울로그 제목 (날짜를 Timestamp로 정렬), 작성시간
//	@GetMapping("/LikesPage")
//	@ResponseBody
//	public Map<String, Object> selectLikesPage(@AuthenticationPrincipal Principal principal, UserPageDTO up) {
//	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
//	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
//
//	    up.setUserNo(userDetails.getUserNo());
//	    up.setStartEnd(up.getPage());
//	    
//	    List<SLBoardDTO> likes = userService.selectLikesPage(up);
//	    
//	    int count = likes.get(0).getTotalCount();
//	    int pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;
//
//	    Map<String, Object> response = new HashMap<>();
//	    response.put("likes", likes);
//	    response.put("pages", pages);
//	    
//	    return response;
//	}
//	
//	// 이벤트 찜 조회 이벤트제목, 작성시간
//	@GetMapping("/HeartBtnPage")
//	@ResponseBody
//	public Map<String, Object> selectHeartBtnPage(@AuthenticationPrincipal Principal principal, UserPageDTO up) {
//	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
//	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
//
//	    up.setUserNo(userDetails.getUserNo());
//	    up.setStartEnd(up.getPage());
//	    
//	    List<SLBoardDTO> heartBtn = userService.selectHeartBtnPage(up);
//	    
//	    int count = heartBtn.get(0).getTotalCount();
//	    int pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;
//
//	    Map<String, Object> response = new HashMap<>();
//	    response.put("heartBtn", heartBtn);
//	    response.put("pages", pages);
//	    
//	    return response;
//	}
//	
//	// 내 문의내역 조회 카테고리이름, 문의제목, 작성시간, 답변여부
//	@GetMapping("/CsQuestionPage")
//	@ResponseBody
//	public Map<String, Object> selectCsQuestionPage(@AuthenticationPrincipal Principal principal, UserPageDTO up) {
//	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
//	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
//
//	    up.setUserNo(userDetails.getUserNo());
//	    up.setStartEnd(up.getPage());
//	    
//	    List<SLBoardDTO> csQuestion = userService.selectCsQuestionPage(up);
//	    
//	    int count = csQuestion.get(0).getTotalCount();
//	    int pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;
//
//	    Map<String, Object> response = new HashMap<>();
//	    response.put("csQuestion", csQuestion);
//	    response.put("pages", pages);
//	    
//	    return response;
//	}
//	
//	// 내 신고내역 조회 신고사유, 신고제목, 작성시간, 답변여부
//	@GetMapping("/ReportPage")
//	@ResponseBody
//	public Map<String, Object> selectReportPage(@AuthenticationPrincipal Principal principal, UserPageDTO up) {
//	    UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
//	    CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
//
//	    up.setUserNo(userDetails.getUserNo());
//	    up.setStartEnd(up.getPage());
//	    
//	    List<SLBoardDTO> report = userService.selectReportPage(up);
//	    
//	    int count = report.get(0).getTotalCount();
//	    int pages = (count % 10 == 0) ? count / 10 : count / 10 + 1;
//
//	    Map<String, Object> response = new HashMap<>();
//	    response.put("report", report);
//	    response.put("pages", pages);
//	    
//	    return response;
//	}
}

