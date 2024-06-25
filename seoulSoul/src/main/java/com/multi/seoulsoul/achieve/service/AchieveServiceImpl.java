package com.multi.seoulsoul.achieve.service;

import java.util.List;

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
		System.out.println("insertAchieveLoca AchieveServiceImpl 도착.");
		
		int result = achieveDAO.insertAchieveLoca(sqlSession, achieveDTO);
		
		System.out.println("result >> " + result);
		
		return result;
	}

	@Override
	public int insertAchieveCate(AchieveDTO achieveDTO) {
		// TODO Auto-generated method stub
		System.out.println("insertAchieveCate AchieveServiceImpl 도착.");
		
		int result = achieveDAO.insertAchieveCate(sqlSession, achieveDTO);
		
		System.out.println("result >> " + result);
		
		return result;
	}

	@Override
	public List<AchieveDTO> achieveLocaList() {
		// TODO Auto-generated method stub
		System.out.println("achieveLocaList AchieveServiceImpl 도착.");

		List<AchieveDTO> achieveLocaList = achieveDAO.achieveLocaList(sqlSession);
		
		return achieveLocaList;
	}
	
	@Override
	public List<AchieveDTO> achieveCateList() {
		// TODO Auto-generated method stub
		System.out.println("achieveCateList AchieveServiceImpl 도착.");

		List<AchieveDTO> achieveCateList = achieveDAO.achieveCateList(sqlSession);
		
		return achieveCateList;
	}

	@Override
	public int deleteAchieveLoca(int achNo) {
		// TODO Auto-generated method stub
		System.out.println("deleteAchieveLoca AchieveServiceImpl 도착.");
		
		int result = achieveDAO.deleteAchieveLoca(sqlSession, achNo);
		
		System.out.println("result >> " + result);
		
		return result;
	}

	@Override
	public int deleteAchieveCate(int achNo) {
		// TODO Auto-generated method stub
		System.out.println("deleteAchieveCate AchieveServiceImpl 도착.");
		
		int result = achieveDAO.deleteAchieveCate(sqlSession, achNo);
		
		System.out.println("result >> " + result);
		
		return result;
	}

}
