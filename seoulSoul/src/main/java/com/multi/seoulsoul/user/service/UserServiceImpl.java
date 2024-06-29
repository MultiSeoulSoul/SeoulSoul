package com.multi.seoulsoul.user.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.soulLog.model.dto.SLBoardDTO;
import com.multi.seoulsoul.soulLog.model.dto.SLReplyDTO;
import com.multi.seoulsoul.user.model.dao.UserDAO;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	private final UserDAO userDAO;

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public boolean isUserIdAvailable(String userId) {
		return !userDAO.findByUserId(sqlSession, userId);
	}
	
	@Override
	public boolean isUserNicknameAvailable(String nickname) {
		return !userDAO.findByUserNickname(sqlSession, nickname);
	}
	
	@Override
	public void joinUser(UserDTO u) throws Exception {
		System.out.println("암호화전 : " + u.getUserPw());
		String encpw = bCryptPasswordEncoder.encode(u.getUserPw());
		System.out.println("암호화후 : " + encpw);
		
		u.setUserPw(encpw);
		int result = userDAO.joinUser(sqlSession, u);
		
		if (result > 0) {
			sqlSession.commit();
		} else {
			throw new Exception("회원가입에 실패 하였습니다");
		}	
	}
	
	@Override
	public void userUpdate(UserDTO u) throws Exception {
		int result = userDAO.userUpdate(sqlSession, u);
		
		if (result > 0) {
			sqlSession.commit();
		} else {
			throw new Exception("회원정보 변경에 실패 하였습니다");
		}
	}
	
	@Override
	public void userPwUpdate(UserDTO u) throws Exception {
		u.setUserPw(bCryptPasswordEncoder.encode(u.getUserPw()));
		int result = userDAO.userPwUpdate(sqlSession, u);
		
		if (result > 0) {
			sqlSession.commit();
		} else {
			throw new Exception("비밀번호 변경에 실패 하였습니다");
		}
	}
	
	@Override
	public void userDelete(int userNo) throws Exception {
		int result = userDAO.userDelete(sqlSession, userNo);
		
		if (result > 0) {
			sqlSession.commit();
		} else {
			throw new Exception("회원 탈퇴에 실패 하였습니다");
		}
	}
	
	@Override
	public List<SLBoardDTO> selectSLBoardPage(UserPageDTO up) {
		List<SLBoardDTO> list = userDAO.selectSLBoardPage(sqlSession, up);
	    return list;
	}

	@Override
	public List<SLReplyDTO> selectSLReplyPage(UserPageDTO up) {
		List<SLReplyDTO> list = userDAO.selectSLReplyPage(sqlSession, up);
		System.out.println("list:"+list);
		return list;
	}
}
