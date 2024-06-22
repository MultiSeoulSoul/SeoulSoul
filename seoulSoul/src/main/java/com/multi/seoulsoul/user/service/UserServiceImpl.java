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
	public UserDTO loginUser(UserDTO u) throws Exception {
		System.out.println("로그인 서비스impl 도착");
		
		UserDTO loginUser = userDAO.loginUser(sqlSession, u);

		System.out.println("로그인 mapper 반환 완료");
		if (loginUser == null) {
			throw new Exception("로그인 정보가 없습니다");
		}
		if (!bCryptPasswordEncoder.matches(u.getUserPw(), loginUser.getUserPw())) {
			throw new Exception("비밀번호가 일치하지 않습니다");
		}

		return loginUser;
	}

}
