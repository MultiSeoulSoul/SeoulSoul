package com.multi.seoulsoul.soulLog.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.soulLog.model.dao.SoulLogDAO;
import com.multi.seoulsoul.soulLog.model.dto.PageDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;

@EnableAspectJAutoProxy // Transaction annotation 방식
@Transactional(rollbackFor = {Exception.class}) // Transaction annotation 방식
@Service
public class SoulLogServiceImpl implements SoulLogService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final SoulLogDAO soulLogDAO;
	
	public SoulLogServiceImpl(SoulLogDAO soulLogDAO) {
		this.soulLogDAO = soulLogDAO; 
	}


	@Override
	public List<SoulLogDTO> selectSoulLogList(PageDTO pageDTO) throws Exception {
		
		return soulLogDAO.selectSoulLogList(sqlSession, pageDTO);
		
	}
	
	
	
	
}