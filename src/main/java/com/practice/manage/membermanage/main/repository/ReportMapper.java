package com.practice.manage.membermanage.main.repository;

import com.practice.manage.membermanage.main.domain.Journal;
import com.practice.manage.membermanage.main.domain.Report;
import com.practice.manage.membermanage.main.domain.Summary;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ReportMapper {
    List<Report> getRecentReportList(String uid, Integer size);
    List<Report> getReportList(String uid, Integer from, Integer size);

    Report getReport(String uid, Integer reportId);

    List<Journal> getJournalListByReportId(String uid, Integer id);

    Summary getSummaryByReportId(String uid, Integer id);

    List<Report> getReportListByMonth(String uid, String month);

    Integer postReport(String uid, Timestamp date);
    void submitReport(String uid, Integer reportId);
    void deleteReport(String uid, Integer reportId);
    void setStatusInProgress(Integer reportId);
}
