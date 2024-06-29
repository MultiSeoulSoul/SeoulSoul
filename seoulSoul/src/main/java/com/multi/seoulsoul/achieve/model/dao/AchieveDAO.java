package com.multi.seoulsoul.achieve.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchCateIconsDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaIconsDTO;
import com.multi.seoulsoul.user.model.dto.UserDTO;

@Repository
public class AchieveDAO {

	public int insertAchieveLoca(SqlSessionTemplate sqlSession, AchLocaDTO achLocaDTO) {
		// TODO Auto-generated method stub
		System.out.println("insertAchieveLoca AchieveDAO 도착.");
		
		return sqlSession.insert("achieveMapper.insertAchieveLoca", achLocaDTO);
	}

	public int insertAchieveCate(SqlSessionTemplate sqlSession, AchCateDTO achCateDTO) {
		// TODO Auto-generated method stub
		System.out.println("insertAchieveCate AchieveDAO 도착.");
		
		return sqlSession.insert("achieveMapper.insertAchieveCate", achCateDTO);
	}
	
	public List<AchLocaDTO> achieveLocaList(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		System.out.println("achieveLocaList AchieveDAO 도착.");

		return sqlSession.selectList("achieveMapper.selectAchieveLoca");
	}
	
	public List<AchCateDTO> achieveCateList(SqlSessionTemplate sqlSession) {
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

	public List<UserDTO> userList(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("userMapper.selectUserList");
	}

	public int insertLocaIcons(SqlSessionTemplate sqlSession, AchLocaIconsDTO achLocaIconsDTO) {
		// TODO Auto-generated method stub
		return sqlSession.insert("achieveMapper.insertLocaIcons", achLocaIconsDTO);
	}

	public int insertCateIcons(SqlSessionTemplate sqlSession, AchCateIconsDTO achCateIconsDTO) {
		// TODO Auto-generated method stub
		return sqlSession.insert("achieveMapper.insertCateIcons", achCateIconsDTO);
	}

	public AchLocaDTO selectAchLocaById(SqlSessionTemplate sqlSession, int achNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("achieveMapper.selectAchLocaById", achNo);
	}

	public AchLocaIconsDTO selectAchLocaIconsByAchNo(SqlSessionTemplate sqlSession, int achNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("achieveMapper.selectAchLocaIconsByAchNo", achNo);
	}
	
    public int updateAchLoca(SqlSessionTemplate sqlSession, AchLocaDTO achLocaDTO) {
        return sqlSession.update("achieveMapper.updateAchLoca", achLocaDTO);
    }

    public int updateLocaIcons(SqlSessionTemplate sqlSession, AchLocaIconsDTO achLocaIconsDTO) {
        return sqlSession.update("achieveMapper.updateLocaIcons", achLocaIconsDTO);
    }
    
    public AchCateDTO selectAchCateById(SqlSessionTemplate sqlSession, int achNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("achieveMapper.selectAchCateById", achNo);
	}

	public AchCateIconsDTO selectAchCateIconsByAchNo(SqlSessionTemplate sqlSession, int achNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("achieveMapper.selectAchCateIconsByAchNo", achNo);
	}
    
    public int updateAchCate(SqlSessionTemplate sqlSession, AchCateDTO achCateDTO) {
        return sqlSession.update("achieveMapper.updateAchLoca", achCateDTO);
    }

    public int updateCateIcons(SqlSessionTemplate sqlSession, AchCateIconsDTO achCateIconsDTO) {
        return sqlSession.update("achieveMapper.updateCateIcons", achCateIconsDTO);
    }

	public List<UserDTO> blackList(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("userMapper.selectBlackList");
	}

	public int updateBlacklistStatus(SqlSessionTemplate sqlSession, int userNo, char status) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<>();
        params.put("userNo", userNo);
        params.put("status", status);
		return sqlSession.update("userMapper.updateBlacklistStatus", params);
	}

}
