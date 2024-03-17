package com.practice.manage.membermanage.main.repository;

import com.practice.manage.membermanage.main.domain.Journal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JournalMapper {
    List<Journal> getJournalList(String uid, Integer reportId, Integer from, Integer size);

    List<Journal> getAllJournalList(String uid, Integer from, Integer size);

    Journal getJournal(String uid, Integer journalId);

    void postJournal(String uid, Journal journal);

    void putJournal(String uid, Integer journalId, Journal journal);

    void deleteJournal(String uid, Integer journalId);

    List<Journal> getAllOldJournalList(String uid, Integer size);

    List<Journal> getOldJournalList(String uid, Integer reportId, Integer size);
}
