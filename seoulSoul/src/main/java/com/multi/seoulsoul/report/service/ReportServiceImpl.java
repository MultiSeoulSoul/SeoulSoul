package com.multi.seoulsoul.report.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
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


	@Override
	public List<ReportDTO> reportList() {
		// TODO Auto-generated method stub
		System.out.println("reportList ReportServiceImpl 도착.");

		List<ReportDTO> reportList = reportDAO.reportList(sqlSession);
		
		return reportList;
	}
	
	@Override
    public ReportDTO getReportById(int reportNo) {
        return reportDAO.selectReportById(sqlSession, reportNo);
    }
	

}
