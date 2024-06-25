package com.multi.seoulsoul.user.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.multi.seoulsoul.soulLog.model.dto.SLBoardDTO;
import com.multi.seoulsoul.user.model.dao.UserDAO;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;

@Service
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
		if(result == 0) {
			throw new Exception("회원가입에 실패 하였습니다");
		}	
	}

	@Override
	public List<SLBoardDTO> selectSLBoardPage(UserPageDTO up) {
		return userDAO.selectSLBoardPage(sqlSession, up);
	}
	
	@Override
	public int selectCount() {
		return userDAO.selectCount(sqlSession);
	}
}
