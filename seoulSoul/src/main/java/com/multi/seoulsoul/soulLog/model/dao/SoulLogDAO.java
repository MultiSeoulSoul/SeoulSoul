package com.multi.seoulsoul.soulLog.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.DetailRequestDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.PageDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;

@Repository
public class SoulLogDAO {
	
	public int selectSoulLogCount(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectOne("soulLogMapper.selectSoulLogCount");
		
	}
	
	public List<SoulLogDTO> selectSoulLogList(SqlSessionTemplate sqlSession, PageDTO pageDTO) {
		
		return sqlSession.selectList("soulLogMapper.selectSoulLogList", pageDTO);
	
	}

	public List<LocationDTO> selectLocationList(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectList("soulLogMapper.selectLocationList");
		
	}

	public List<CategoryDTO> selectCategoryList(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectList("soulLogMapper.selectCategoryList");
		
	}

	public int insertSoulLog(SqlSessionTemplate sqlSession, SoulLogDTO soulLogDTO) {
		
		return sqlSession.insert("soulLogMapper.insertSoulLog", soulLogDTO);
		
	}

	public int insertSoulLogFile(SqlSessionTemplate sqlSession, FilesDTO filesDTO) {
		
		return sqlSession.insert("soulLogMapper.insertSoulLogFile", filesDTO);
		
	}

	public SoulLogDTO soulLogDetail(SqlSessionTemplate sqlSession, DetailRequestDTO detailRequestDTO) {
		
		return sqlSession.selectOne("soulLogMapper.soulLogDetail", detailRequestDTO);
		
	}

	public int addViews(SqlSessionTemplate sqlSession, int soulLogNo) {
		
		return sqlSession.update("soulLogMapper.addViews", soulLogNo);
		
	}

	

}
