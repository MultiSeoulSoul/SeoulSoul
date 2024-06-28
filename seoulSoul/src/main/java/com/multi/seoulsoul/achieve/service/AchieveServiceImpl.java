package com.multi.seoulsoul.achieve.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.achieve.model.dao.AchieveDAO;
import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchCateIconsDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaIconsDTO;
import com.multi.seoulsoul.user.model.dto.UserDTO;

@Service
@Transactional(rollbackFor = Exception.class)
public class AchieveServiceImpl implements AchieveService {
	
	private final AchieveDAO achieveDAO;
	@Autowired
	public AchieveServiceImpl(AchieveDAO achieveDAO) {
		this.achieveDAO = achieveDAO;
	}
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
    public int insertAchieveLoca(AchLocaDTO achLocaDTO) throws Exception {
        int result = achieveDAO.insertAchieveLoca(sqlSession, achLocaDTO);
        if (result <= 0) {
            throw new Exception("업적 생성 실패.");
        }
        return result;
    }

    @Override
    public int insertLocaIcons(AchLocaIconsDTO achLocaIconsDTO) throws Exception {
        int result = achieveDAO.insertLocaIcons(sqlSession, achLocaIconsDTO);
        if (result <= 0) {
            throw new Exception("아이콘 삽입 실패.");
        }
        return result;
    }

	@Override
	public int insertAchieveCate(AchCateDTO achCateDTO) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("insertAchieveCate AchieveServiceImpl 도착.");
		
		int result = achieveDAO.insertAchieveCate(sqlSession, achCateDTO);
		
		System.out.println("result >> " + result);
		
		return result;
	}

	@Override
	public int insertCateIcons(AchCateIconsDTO achCateIconsDTO) throws Exception {
		int result = achieveDAO.insertCateIcons(sqlSession, achCateIconsDTO);
        if (result <= 0) {
            throw new Exception("아이콘 삽입 실패.");
        }
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
    public AchLocaDTO getAchLocaById(int achNo) {
        return achieveDAO.selectAchLocaById(sqlSession, achNo);
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

	@Override
	public List<UserDTO> userList() {
		// TODO Auto-generated method stub
		System.out.println("userList AchieveServiceImpl 도착.");

		List<UserDTO> userList = achieveDAO.userList(sqlSession);
		
		return userList;
	}

}
