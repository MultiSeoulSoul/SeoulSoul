package com.multi.seoulsoul.report.service;

import java.util.List;

import com.multi.seoulsoul.report.model.dto.ReportDTO;
import com.multi.seoulsoul.report.model.dto.ReportedSoulLogDTO;

public interface ReportService {

	ReportedSoulLogDTO selectReportedSoulLog(int soulLogNo) throws Exception;

	void insertSoulLogReport(ReportDTO reportDTO) throws Exception;

	List<ReportDTO> reportList();

	ReportDTO getReportById(int reportNo);

}
