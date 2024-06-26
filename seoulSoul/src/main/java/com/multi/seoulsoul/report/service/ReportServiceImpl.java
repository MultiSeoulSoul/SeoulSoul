package com.multi.seoulsoul.report.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.report.model.dao.ReportDAO;
import com.multi.seoulsoul.report.model.dto.ReportDTO;
import com.multi.seoulsoul.report.model.dto.ReportedSoulLogDTO;

@Service
public class ReportServiceImpl implements ReportService {
	
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final ReportDAO reportDAO;
	
	public ReportServiceImpl(ReportDAO reportDAO) {
		this.reportDAO = reportDAO; 
	}

	
	@Override
	public ReportedSoulLogDTO selectReportedSoulLog(int soulLogNo) throws Exception {
		
		return reportDAO.selectReportedSoulLog(sqlSession, soulLogNo);
		
	}


	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void insertSoulLogReport(ReportDTO reportDTO) throws Exception {
		
		reportDAO.insertSoulLogReport(sqlSession, reportDTO);
		
	}
	

	

}
