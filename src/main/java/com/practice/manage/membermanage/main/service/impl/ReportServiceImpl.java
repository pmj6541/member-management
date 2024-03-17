package com.practice.manage.membermanage.main.service.impl;

import com.practice.manage.membermanage.main.repository.ReportMapper;
import com.practice.manage.membermanage.main.service.ReportService;
import com.practice.manage.membermanage.main.domain.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    final ReportMapper reportMapper;

    @Override
    public List<Report> getReportList(String uid, Integer from, Integer size) {
        List<Report> reportList;
        if(from < 0){
            reportList = reportMapper.getRecentReportList(uid, size);
        }else{
            reportList = reportMapper.getReportList(uid, from, size);
        }

        for(Report i : reportList){
            i.setSummary(reportMapper.getSummaryByReportId(uid, i.getId()));
            i.setJournals(reportMapper.getJournalListByReportId(uid, i.getId()));
        }
        return reportList;
    }

    @Override
    public Report getReport(String uid, Integer reportId) {
        Report report = reportMapper.getReport(uid, reportId);
        report.setSummary(reportMapper.getSummaryByReportId(uid, reportId));
        report.setJournals(reportMapper.getJournalListByReportId(uid, reportId));
        return report;
    }

    @Override
    public List<Report> getReportListByMonth(String uid, String month) {
        List<Report> reportList = reportMapper.getReportListByMonth(uid, month);
        for(Report i : reportList){
            i.setSummary(reportMapper.getSummaryByReportId(uid, i.getId()));
            i.setJournals(reportMapper.getJournalListByReportId(uid, i.getId()));
        }
        return reportList;
    }

    @Override
    public Integer postReport(String uid, Timestamp date) {
        return reportMapper.postReport(uid, date);
    }

    @Override
    public void submitReport(String uid, Integer reportId) {
        reportMapper.submitReport(uid, reportId);
    }

    @Override
    public void deleteReport(String uid, Integer reportId) {
        reportMapper.deleteReport(uid, reportId);
    }
}
