package com.multi.seoulsoul.user.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.soulLog.model.dto.StatsDTO;
import com.multi.seoulsoul.user.model.dto.UserDTO;
import com.multi.seoulsoul.user.model.dto.UserPageDTO;
import com.multi.seoulsoul.user.model.dto.UserProfileDTO;
import com.multi.seoulsoul.user.tempDTO.AchievementDTO;
import com.multi.seoulsoul.user.tempDTO.SLBoardDTO;
import com.multi.seoulsoul.user.tempDTO.SLReplyDTO;

@Repository
public class UserDAO {
	
	// 회원 가입
	public int joinUser(SqlSessionTemplate sqlSession, UserDTO u) {
		int result = sqlSession.insert("userMapper.joinUser", u);
	    int userNo = sqlSession.selectOne("userMapper.selectLastInsertId");
	    sqlSession.insert("userMapper.insertUserRole", userNo);
	    sqlSession.insert("userMapper.insertUserProfile", userNo);
	 
	    sqlSession.insert("userMapper.insertAchLocaCount", userNo);
	    sqlSession.insert("userMapper.insertAchCateCount", userNo);
	    sqlSession.insert("userMapper.insertAchLocaGet", userNo);
	    sqlSession.insert("userMapper.insertAchCateGet", userNo);
	    
		return result;
	}
	
	// 회원정보 업데이트
	public int userUpdate(SqlSessionTemplate sqlSession, UserDTO u) {
		return sqlSession.update("userMapper.userUpdate", u);
	}

	// 비밀번호 업데이트
	public int userPwUpdate(SqlSessionTemplate sqlSession, UserDTO u) {
		return sqlSession.update("userMapper.userPwUpdate", u);
	}
	
	// 프로필 업데이트
	public int updateProfile(SqlSessionTemplate sqlSession, UserProfileDTO up) {
		return sqlSession.update("userMapper.updateProfile", up);
	}

	// 회원 탈퇴
	public int userDelete(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.delete("userMapper.userDelete", userNo);
	}
	
	// 아이디 중복조회
	public boolean findByUserId(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.selectOne("userMapper.findByUserId", userId);
	}
	
	// 닉네임 중복조회
	public boolean findByUserNickname(SqlSessionTemplate sqlSession, String nickname) {
		return sqlSession.selectOne("userMapper.findByNickname", nickname);
	}

	// 시큐리티 사용자 객체 받아오기
	public UserDTO findUserByUsername(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.selectOne("userMapper.findUserByUsername", userId);
	}

	// 시큐리티 사용자 권한 받아오기
	public List<String> findAuthoritiesByUserNo(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectList("userMapper.findAuthoritiesByUserNo", userNo);
	}

	// 소울로그 조회
	public List<SLBoardDTO> selectSLBoardPage(SqlSessionTemplate sqlSession, UserPageDTO up) {
		return sqlSession.selectList("userMapper.selectSLBoardPage", up);
	}

	// 소울로그 댓글 조회
	public List<SLReplyDTO> selectSLReplyPage(SqlSessionTemplate sqlSession, UserPageDTO up) {
		return sqlSession.selectList("userMapper.selectSLReplyPage", up);
	}

	public List<?> selectEventReplyPage(SqlSessionTemplate sqlSession, UserPageDTO up) {
		return sqlSession.selectList("userMapper.selectEventReplyPage", up);
	}

	public List<?> selectLikesPage(SqlSessionTemplate sqlSession, UserPageDTO up) {
		return sqlSession.selectList("userMapper.selectLikesPage", up);
	}

	public List<?> selectHeartBtnPage(SqlSessionTemplate sqlSession, UserPageDTO up) {
		return sqlSession.selectList("userMapper.selectHeartBtnPage", up);
	}

	public List<?> selectCsQuestionPage(SqlSessionTemplate sqlSession, UserPageDTO up) {
		return sqlSession.selectList("userMapper.selectCsQuestionPage", up);
	}

	public List<?> selectReportPage(SqlSessionTemplate sqlSession, UserPageDTO up) {
		return sqlSession.selectList("userMapper.selectReportPage", up);
	}

    public List<AchievementDTO> getAchievement(SqlSessionTemplate sqlSession, int userNo) {
        List<AchievementDTO> list1 = sqlSession.selectList("userMapper.getLocaAchievement", userNo);
        List<AchievementDTO> list2 = sqlSession.selectList("userMapper.getCateAchievement", userNo);

        List<AchievementDTO> list = new ArrayList<>(list1);
        list.addAll(list2);
        
        return list;
    }

	public List<StatsDTO> getSoul(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectList("soulLogMapper.selectStats", userNo);
	}
}
