package com.practice.manage.membermanage.main.service.impl;

import com.practice.manage.membermanage.main.repository.JournalMapper;
import com.practice.manage.membermanage.main.repository.ReportMapper;
import com.practice.manage.membermanage.main.service.JournalService;
import com.practice.manage.membermanage.main.domain.Journal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {
    final JournalMapper journalMapper;
    final ReportMapper reportMapper;
    @Override
    public List<Journal> getJournalList(String uid, Integer reportId, Integer from, Integer size) {
        if(reportId == -1){
            if(from < 0){
                return journalMapper.getAllOldJournalList(uid, size);
            }else{
                return journalMapper.getAllJournalList(uid, from, size);
            }

        }else{
            if(from < 0){
                return journalMapper.getOldJournalList(uid, reportId, size);
            }else{
                return journalMapper.getJournalList(uid, reportId, from, size);
            }

        }

    }

    @Override
    public void postJournal(String uid, Journal journal) {
        reportMapper.setStatusInProgress(journal.getReportId());
        journalMapper.postJournal(uid, journal);
    }

    @Override
    public Journal getJournal(String uid, Integer journalId) {
        return journalMapper.getJournal(uid, journalId);
    }

    @Override
    public void putJournal(String uid, Integer journalId, Journal journal) {
        journalMapper.putJournal(uid, journalId, journal);
    }

    @Override
    public void deleteJournal(String uid, Integer journalId) {
        journalMapper.deleteJournal(uid, journalId);
    }
}
