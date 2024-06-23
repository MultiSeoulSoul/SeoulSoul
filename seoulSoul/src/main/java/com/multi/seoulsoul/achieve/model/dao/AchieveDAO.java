package com.multi.seoulsoul.achieve.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.achieve.model.dto.AchieveDTO;

@Repository
public class AchieveDAO {

	public int insertAchieveLoca(SqlSessionTemplate sqlSession, AchieveDTO achieveDTO) {
		// TODO Auto-generated method stub
		System.out.println("AchieveDAO 도착.");
		return sqlSession.insert("achieveMapper.insertAchieveLoca", achieveDTO);
	}

}
