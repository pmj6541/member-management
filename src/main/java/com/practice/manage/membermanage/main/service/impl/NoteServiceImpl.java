package com.practice.manage.membermanage.main.service.impl;

import com.practice.manage.membermanage.main.repository.NoteMapper;
import com.practice.manage.membermanage.main.repository.ReportMapper;
import com.practice.manage.membermanage.main.service.NoteService;
import com.practice.manage.membermanage.main.domain.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    final NoteMapper noteMapper;
    final ReportMapper reportMapper;
    @Override
    public List<Note> getNoteList(String uid, Integer reportId, Integer from, Integer size) {
        if(reportId == -1){
            return noteMapper.getAllNoteList(uid, from, size);
        }else {
            return noteMapper.getNoteList(uid, reportId, from, size);
        }
    }

    @Override
    public Note getNote(String uid, Integer noteId) {
        return noteMapper.getNote(uid, noteId);
    }

    @Override
    public void postNote(String uid, Note note) {
        reportMapper.setStatusInProgress(note.getReportId());
        noteMapper.postNote(uid, note);
    }

    @Override
    public void putNote(String uid, Integer noteId, Note note) {
        noteMapper.putNote(uid, noteId, note);
    }

    @Override
    public void deleteNote(String uid, Integer noteId) {
        noteMapper.deleteNote(uid, noteId);
    }
}
