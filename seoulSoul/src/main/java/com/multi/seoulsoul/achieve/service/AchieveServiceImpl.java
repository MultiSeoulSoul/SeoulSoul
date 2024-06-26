package com.multi.seoulsoul.achieve.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.seoulsoul.achieve.model.dao.AchieveDAO;
import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;

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
	public int insertAchieveLoca(AchLocaDTO achLocaDTO) {
		// TODO Auto-generated method stub
		System.out.println("insertAchieveLoca AchieveServiceImpl 도착.");
		
		int result = achieveDAO.insertAchieveLoca(sqlSession, achLocaDTO);
		
		System.out.println("result >> " + result);
		
		return result;
	}

	@Override
	public int insertAchieveCate(AchCateDTO achCateDTO) {
		// TODO Auto-generated method stub
		System.out.println("insertAchieveCate AchieveServiceImpl 도착.");
		
		int result = achieveDAO.insertAchieveCate(sqlSession, achCateDTO);
		
		System.out.println("result >> " + result);
		
		return result;
	}

	@Override
	public List<AchLocaDTO> achieveLocaList() {
		// TODO Auto-generated method stub
		System.out.println("achieveLocaList AchieveServiceImpl 도착.");

		List<AchLocaDTO> achieveLocaList = achieveDAO.achieveLocaList(sqlSession);
		
		return achieveLocaList;
	}
	
	@Override
	public List<AchCateDTO> achieveCateList() {
		// TODO Auto-generated method stub
		System.out.println("achieveCateList AchieveServiceImpl 도착.");

		List<AchCateDTO> achieveCateList = achieveDAO.achieveCateList(sqlSession);
		
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
