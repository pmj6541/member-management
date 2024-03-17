package com.practice.manage.membermanage.main.service;

import com.practice.manage.membermanage.main.domain.Note;

import java.util.List;

public interface NoteService {
    List<Note> getNoteList(String uid, Integer reportId, Integer from, Integer size);

    Note getNote(String uid, Integer noteId);

    void postNote(String uid, Note note);

    void putNote(String uid, Integer noteId, Note note);

    void deleteNote(String uid, Integer noteId);
}
