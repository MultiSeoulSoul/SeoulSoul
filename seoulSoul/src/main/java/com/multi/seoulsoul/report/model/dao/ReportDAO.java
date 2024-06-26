package com.multi.seoulsoul.report.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.report.model.dto.ReportDTO;
import com.multi.seoulsoul.report.model.dto.ReportedSoulLogDTO;

@Repository
public class ReportDAO {

	public ReportedSoulLogDTO selectReportedSoulLog(SqlSessionTemplate sqlSession, int soulLogNo) {
		
		return sqlSession.selectOne("reportMapper.selectReportedSoulLog", soulLogNo);
		
	}

	public int insertSoulLogReport(SqlSessionTemplate sqlSession, ReportDTO reportDTO) {
		
		return sqlSession.insert("reportMapper.insertSoulLogReport", reportDTO);
		
	}

}
