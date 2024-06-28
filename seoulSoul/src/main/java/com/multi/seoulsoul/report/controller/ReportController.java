package com.multi.seoulsoul.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.seoulsoul.report.model.dto.ReportDTO;
import com.multi.seoulsoul.report.model.dto.ReportedSoulLogDTO;
import com.multi.seoulsoul.report.model.dto.ReporterDTO;
import com.multi.seoulsoul.report.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {

	
	private final ReportService reportService;
	
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	
	@RequestMapping("/soulLogReportForm")
	public String soulLogReportForm(int soulLogNo, Model model) {
		
		System.out.println("신고할 로그 no는 >>>>> " + soulLogNo);
		
		try {
			
			ReportedSoulLogDTO reportedSoulLog = reportService.selectReportedSoulLog(soulLogNo);
			
			model.addAttribute("reportedSoulLog", reportedSoulLog);
			
			return "report/soulLogReportForm";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "신고할 로그 조회 실패..");
			
			return "common/errorPage";
			
		}
		
	}
	
	
	@PostMapping("/insertSoulLogReport")
	public String insertSoulLogReport(ReportDTO reportDTO, ReportedSoulLogDTO reportedSoulLogDTO, ReporterDTO reporterDTO, Model model) {
		
		System.out.println("reportDTO는 >>>>> " + reportDTO);
		System.out.println("reportedSoulLogDTO는 >>>>> " + reportedSoulLogDTO);
		System.out.println("reporterDTO는 >>>>> " + reporterDTO);
		
		reportDTO.setReportedSoulLog(reportedSoulLogDTO);
		reportDTO.setReporter(reporterDTO);
		
		try {
			
			reportService.insertSoulLogReport(reportDTO);
			
			return "redirect:/soulLog/soulLogMain?page=1";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "신고글 작성 실패..");
			
			return "common/errorPage";
			
		}
		
		
	}
	
	@GetMapping("/reportDetail")
	public String reportDetail(@RequestParam("reportNo") int reportNo, Model model) {
        ReportDTO report = reportService.getReportById(reportNo);
        model.addAttribute("report", report);
        return "/report/reportDetail";
    }
	
	
}
