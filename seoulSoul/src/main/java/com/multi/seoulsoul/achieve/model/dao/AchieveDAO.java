package com.multi.seoulsoul.achieve.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.achieve.model.dto.AchieveDTO;

@Repository
public class AchieveDAO {

	public int insertAchieveLoca(SqlSessionTemplate sqlSession, AchieveDTO achieveDTO) {
		// TODO Auto-generated method stub
		System.out.println("insertAchieveLoca AchieveDAO 도착.");
		
		return sqlSession.insert("achieveMapper.insertAchieveLoca", achieveDTO);
	}

	public int insertAchieveCate(SqlSessionTemplate sqlSession, AchieveDTO achieveDTO) {
		// TODO Auto-generated method stub
		System.out.println("insertAchieveCate AchieveDAO 도착.");
		
		return sqlSession.insert("achieveMapper.insertAchieveCate", achieveDTO);
	}
	
	public List<AchieveDTO> achieveLocaList(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		System.out.println("achieveLocaList AchieveDAO 도착.");

		return sqlSession.selectList("achieveMapper.selectAchieveLoca");
	}
	
	public List<AchieveDTO> achieveCateList(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		System.out.println("achieveCateList AchieveDAO 도착.");

		return sqlSession.selectList("achieveMapper.selectAchieveCate");
	}

	public int deleteAchieveLoca(SqlSessionTemplate sqlSession, int achNo) {
		// TODO Auto-generated method stub
		System.out.println("deleteAchieveLoca AchieveDAO 도착.");
		
		return sqlSession.delete("achieveMapper.deleteAchieveLoca", achNo);
	}

	public int deleteAchieveCate(SqlSessionTemplate sqlSession, int achNo) {
		// TODO Auto-generated method stub
		System.out.println("deleteAchieveCate AchieveDAO 도착.");
		
		return sqlSession.delete("achieveMapper.deleteAchieveCate", achNo);
	}

}
