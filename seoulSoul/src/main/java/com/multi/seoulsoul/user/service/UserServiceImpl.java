package com.multi.seoulsoul.user.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.multi.seoulsoul.user.model.dao.UserDAO;
import com.multi.seoulsoul.user.model.dto.UserDTO;

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

//	@Override
//	public UserDTO loginUser(UserDTO u) throws Exception {
//		
//		UserDTO loginUser = userDAO.loginUser(sqlSession, u);
//		
//		if(loginUser == null) {
//			throw new Exception("로그인 정보가 없습니다"); 
//		}
//		if(!bCryptPasswordEncoder.matches(u.getUserPw(), loginUser.getUserPw())) {
//            System.out.println("비밀번호가 일치하지 않습니다");
//			throw new Exception("비밀번호가 일치하지 않습니다.");
//        }
//		return loginUser;
//	}


}
