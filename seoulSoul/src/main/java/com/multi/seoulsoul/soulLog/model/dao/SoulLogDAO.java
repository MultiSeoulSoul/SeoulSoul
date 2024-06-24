package com.multi.seoulsoul.soulLog.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

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

	

}
