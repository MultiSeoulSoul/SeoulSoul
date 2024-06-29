package com.multi.seoulsoul.user.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.user.model.dao.UserDAO;
import com.multi.seoulsoul.user.model.dto.CustomUserDetails;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;
import com.multi.seoulsoul.user.model.dto.UserProfileDTO;
import com.multi.seoulsoul.user.tempDTO.SLBoardDTO;
import com.multi.seoulsoul.user.tempDTO.SLReplyDTO;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	private final UserDAO userDAO;
	private final CustomUserDetailsService customUserDetailsService;
	private final SqlSessionTemplate sqlSession;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(
			UserDAO userDAO,
			CustomUserDetailsService customUserDetailsService,
			SqlSessionTemplate sqlSession,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDAO = userDAO;
		this.customUserDetailsService = customUserDetailsService;
		this.sqlSession = sqlSession;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	// 회원가입
	@Override
	public void joinUser(UserDTO u) throws Exception {
		String encpw = bCryptPasswordEncoder.encode(u.getUserPw());
		u.setUserPw(encpw);
		int result = userDAO.joinUser(sqlSession, u);
		if (result <= 0) {
			throw new Exception("회원가입에 실패 하였습니다");
		}
	}
	
	// 회원정보 업데이트
	@Override
	public void userUpdate(UserDTO u) throws Exception {
		int result = userDAO.userUpdate(sqlSession, u);
		if (result <= 0) {
			throw new Exception("회원정보 변경에 실패 하였습니다");
		}
	}
	
	// 비밀번호 업데이트
	@Override
	public void userPwUpdate(UserDTO u) throws Exception {
		u.setUserPw(bCryptPasswordEncoder.encode(u.getUserPw()));
		int result = userDAO.userPwUpdate(sqlSession, u);
		if (result <= 0) {
			throw new Exception("비밀번호 변경에 실패 하였습니다");
		}
	}
	
	// 프로필 업데이트
	@Override
	public void updateProfile(UserProfileDTO up) throws Exception {
		int result = userDAO.updateProfile(sqlSession, up);	
		if (result <= 0) {
			throw new Exception("프로필 수정에 실패했습니다");
		}
	}
	
	// 회원 탈퇴
	@Override
	public void userDelete(int userNo) throws Exception {
		int result = userDAO.userDelete(sqlSession, userNo);
		if (result <= 0) {
			throw new Exception("회원 탈퇴에 실패 하였습니다");
		}
	}
	
	// 아이디 중복조회
	@Override
	public boolean isUserIdAvailable(String userId) {
		return !userDAO.findByUserId(sqlSession, userId);
	}
	
	// 닉네임 중복조회
	@Override
	public boolean isUserNicknameAvailable(String nickname) {
		return !userDAO.findByUserNickname(sqlSession, nickname);
	}
	
	// 소울로그 조회
	@Override
	public List<SLBoardDTO> selectSLBoardPage(UserPageDTO up) {
		List<SLBoardDTO> list = userDAO.selectSLBoardPage(sqlSession, up);
	    return list;
	}

	// 소울로그 댓글 조회
	@Override
	public List<SLReplyDTO> selectSLReplyPage(UserPageDTO up) {
		List<SLReplyDTO> list = userDAO.selectSLReplyPage(sqlSession, up);
		return list;
	}
	
	@Override
	public List<?> selectEventReplyPage(UserPageDTO up) {
		return userDAO.selectEventReplyPage(sqlSession, up);
	}

	@Override
	public List<?> selectLikesPage(UserPageDTO up) {
		return userDAO.selectLikesPage(sqlSession, up);
	}

	@Override
	public List<?> selectHeartBtnPage(UserPageDTO up) {
		return userDAO.selectHeartBtnPage(sqlSession, up);
	}

	@Override
	public List<?> selectCsQuestionPage(UserPageDTO up) {
		return userDAO.selectCsQuestionPage(sqlSession, up);
	}

	@Override
	public List<?> selectReportPage(UserPageDTO up) {
		return userDAO.selectReportPage(sqlSession, up);
	}
	
	// 사용자 세션 업데이트
	@Override
	public void updateCustomUserDetails(String userId) {
		CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
        		customUserDetails, 
        		customUserDetails.getPassword(), 
        		customUserDetails.getAuthorities()
            );
        SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
