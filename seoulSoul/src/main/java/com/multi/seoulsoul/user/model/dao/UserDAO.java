package com.multi.seoulsoul.user.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.user.model.dto.UserDTO;

@Repository
public class UserDAO {

	// 아이디 중복 확인 메서드
	public boolean findByUserId(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.selectOne("userMapper.findByUserId", userId);
	}

	public boolean findByUserNickname(SqlSessionTemplate sqlSession, String nickname) {
		return sqlSession.selectOne("userMapper.findByNickname", nickname);
	}

	public int joinUser(SqlSessionTemplate sqlSession, UserDTO u) {
		return sqlSession.insert("userMapper.joinUser", u);
	}

//	public UserDTO loginUser(SqlSessionTemplate sqlSession, UserDTO u) {
//		return sqlSession.selectOne("userMapper.loginUser", u);
//	}
}
