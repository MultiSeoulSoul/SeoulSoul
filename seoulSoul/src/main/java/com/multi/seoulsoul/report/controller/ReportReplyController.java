package com.multi.seoulsoul.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.seoulsoul.report.model.dto.ReportDTO;
import com.multi.seoulsoul.report.model.dto.ReportReplyDTO;
import com.multi.seoulsoul.report.service.ReportService;

@Controller
@RequestMapping("/reportReply")
public class ReportReplyController {
	
    private final ReportService reportService;
    
    @Autowired
    public ReportReplyController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@GetMapping("/reportDetail")
	public String reportDetail(int reportNo, Model model) {
        
		try {
			ReportDTO report = reportService.getReportById(reportNo);
			List<ReportReplyDTO> reportReply = reportService.selectReportReply(reportNo);
			
	        model.addAttribute("report", report);
	        model.addAttribute("reportReply", reportReply);
	        
	        return "/report/reportDetail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("msg", "신고 상세 페이지 조회 실패..");
			return "/common/errorPage";
		}
    }
    
    @PostMapping("/insertReportReply")
    public String insertReportReply(ReportReplyDTO reportReplyDTO, Model model) {
    	System.out.println("reportReplyDTO : " + reportReplyDTO);
    	try {
        	int result = reportService.insertReportReply(reportReplyDTO);
        	if (result <= 0) {
        		System.out.println("답변 달기 실패");
        		return "/common/errorPage";
        	} else {
        		System.out.println("답변 달기 성공");
        	}
    	} catch (Exception e) {
    		e.printStackTrace();
    		model.addAttribute("msg", "신고 답변 달기 실패..");
    		return "/common/errorPage";
    	}
    	return "redirect:/reportReply/reportDetail?reportNo=" + reportReplyDTO.getReportNo();
    }
}
