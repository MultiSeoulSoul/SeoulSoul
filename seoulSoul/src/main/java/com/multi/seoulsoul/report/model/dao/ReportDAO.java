package com.multi.seoulsoul.report.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.multi.seoulsoul.report.model.dto.ReportDTO;
import com.multi.seoulsoul.report.model.dto.ReportReplyDTO;
import com.multi.seoulsoul.report.model.dto.ReportedSoulLogDTO;

@Repository
public class ReportDAO {

	public ReportedSoulLogDTO selectReportedSoulLog(SqlSessionTemplate sqlSession, int soulLogNo) {
		
		return sqlSession.selectOne("reportMapper.selectReportedSoulLog", soulLogNo);
		
	}

	public int insertSoulLogReport(SqlSessionTemplate sqlSession, ReportDTO reportDTO) {
		
		return sqlSession.insert("reportMapper.insertSoulLogReport", reportDTO);
		
	}

	public List<ReportDTO> reportList(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("reportMapper.selectReportList");
	}

	public ReportDTO selectReportById(SqlSessionTemplate sqlSession, int reportNo) {
        return sqlSession.selectOne("reportMapper.selectReportById", reportNo);
    }

	public int insertReportReply(SqlSessionTemplate sqlSession, ReportReplyDTO reportReplyDTO) {
		// TODO Auto-generated method stub
		return sqlSession.insert("reportMapper.insertReportReply", reportReplyDTO);
	}

	public List<ReportReplyDTO> selectReportReply(SqlSessionTemplate sqlSession, int reportNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("reportMapper.selectReportReply", reportNo);
	}
}
