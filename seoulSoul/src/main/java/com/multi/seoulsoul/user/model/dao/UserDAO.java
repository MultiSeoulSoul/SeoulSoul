package com.multi.seoulsoul.user.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.soulLog.model.dto.SLBoardDTO;
import com.multi.seoulsoul.soulLog.model.dto.SLReplyDTO;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;
import com.multi.seoulsoul.user.model.dto.UserProfileDTO;

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
		int result = sqlSession.insert("userMapper.joinUser", u);
	    int userNo = sqlSession.selectOne("userMapper.selectLastInsertId");
	    sqlSession.insert("userMapper.insertUserRole", userNo);
	    sqlSession.insert("userMapper.insertUserProfile", userNo);
		return result;
	}

	public UserDTO findUserByUsername(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.selectOne("userMapper.findUserByUsername", userId);
	}

	public List<String> findAuthoritiesByUserNo(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectList("userMapper.findAuthoritiesByUserNo", userNo);
	}

	public List<SLBoardDTO> selectSLBoardPage(SqlSessionTemplate sqlSession, UserPageDTO up) {
		return sqlSession.selectList("userMapper.selectSLBoardPage", up);
	}

	public List<SLReplyDTO> selectSLReplyPage(SqlSessionTemplate sqlSession, UserPageDTO up) {
		return sqlSession.selectList("userMapper.selectSLReplyPage", up);
	}

	public int userUpdate(SqlSessionTemplate sqlSession, UserDTO u) {
		return sqlSession.update("userMapper.userUpdate", u);
	}

	public int userPwUpdate(SqlSessionTemplate sqlSession, UserDTO u) {
		return sqlSession.update("userMapper.userPwUpdate", u);
	}

	public int userDelete(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.delete("userMapper.userDelete", userNo);
	}

	public int updateProfile(SqlSessionTemplate sqlSession, UserProfileDTO up) {
		return sqlSession.update("userMapper.updateProfile", up);
	}
}
