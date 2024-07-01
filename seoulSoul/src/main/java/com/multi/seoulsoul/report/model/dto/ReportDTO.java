package com.multi.seoulsoul.report.model.dto;

import java.sql.Timestamp;
import java.util.List;

public class ReportDTO {
	
	private int reportNo;
	private String title;
	private String reason;
	private Timestamp createdDate;
	private ReporterDTO reporter;
	private ReportedSoulLogDTO reportedSoulLog;
	private List<ReportReplyDTO> reportReply;
	
	
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public ReporterDTO getReporter() {
		return reporter;
	}
	public void setReporter(ReporterDTO reporter) {
		this.reporter = reporter;
	}
	public ReportedSoulLogDTO getReportedSoulLog() {
		return reportedSoulLog;
	}
	public void setReportedSoulLog(ReportedSoulLogDTO reportedSoulLog) {
		this.reportedSoulLog = reportedSoulLog;
	}
	public List<ReportReplyDTO> getReportReply() {
		return reportReply;
	}
	public void setReportReply(List<ReportReplyDTO> reportReply) {
		this.reportReply = reportReply;
	}
	
	
	@Override
	public String toString() {
		return "ReportDTO [reportNo=" + reportNo + ", title=" + title + ", reason=" + reason + ", createdDate="
				+ createdDate + ", reporter=" + reporter + ", reportedSoulLog=" + reportedSoulLog + ", reportReply="
				+ reportReply + "]";
	}
	
}
