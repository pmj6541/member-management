package com.practice.manage.membermanage.main.repository;

import com.practice.manage.membermanage.main.domain.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMapper {
    List<Note> getNoteList(String uid, Integer reportId, Integer from, Integer size);

    Note getNote(String uid, Integer noteId);

    List<Note> getAllNoteList(String uid, Integer from, Integer size);

    void postNote(String uid, Note note);

    void putNote(String uid, Integer noteId, Note note);

    void deleteNote(String uid, Integer noteId);



}
