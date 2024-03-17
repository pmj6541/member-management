package com.practice.manage.membermanage.main.service;

import com.practice.manage.membermanage.main.domain.Report;

import java.sql.Timestamp;
import java.util.List;

public interface ReportService {
    List<Report> getReportList(String uid, Integer from, Integer size);
    Report getReport(String uid, Integer reportId);
    List<Report> getReportListByMonth(String uid, String month);
    Integer postReport(String uid, Timestamp date);
    void submitReport(String uid, Integer reportId);
    void deleteReport(String uid, Integer reportId);

}
