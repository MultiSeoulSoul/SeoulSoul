package com.multi.seoulsoul.achieve.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.seoulsoul.achieve.model.dao.AchieveDAO;
import com.multi.seoulsoul.achieve.model.dto.AchieveDTO;

@Service
public class AchieveServiceImpl implements AchieveService {
	
	private final AchieveDAO achieveDAO;
	@Autowired
	public AchieveServiceImpl(AchieveDAO achieveDAO) {
		this.achieveDAO = achieveDAO;
	}
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertAchieveLoca(AchieveDTO achieveDTO) {
		// TODO Auto-generated method stub
		System.out.println("AchieveServiceImpl 도착.");
		
		int result = achieveDAO.insertAchieveLoca(sqlSession, achieveDTO);
		
		System.out.println("result >> " + result);
		
		return result;
	}

}
