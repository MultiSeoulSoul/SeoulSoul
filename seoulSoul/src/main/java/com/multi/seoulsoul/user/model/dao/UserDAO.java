package com.multi.seoulsoul.user.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.user.model.dto.UserDTO;

@Repository
public class UserDAO {

	public UserDTO loginUser(SqlSessionTemplate sqlSession, UserDTO u) {
		System.out.println("로그인 DAO 도착");
		return sqlSession.selectOne("userMapper.loginUser", u);
	}

}
