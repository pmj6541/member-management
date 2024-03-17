package com.practice.manage.membermanage.main.service;

import com.practice.manage.membermanage.main.domain.Journal;

import java.util.List;

public interface JournalService {
    List<Journal> getJournalList(String uid, Integer reportId, Integer from, Integer size);

    void postJournal(String uid, Journal journal);

    Journal getJournal(String uid, Integer journalId);

    void putJournal(String uid, Integer journalId, Journal journal);

    void deleteJournal(String uid, Integer journalId);


}
