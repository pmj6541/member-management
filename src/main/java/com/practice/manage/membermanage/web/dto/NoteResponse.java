package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Note;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "기타사항 응답")
public class NoteResponse {
    @Schema(description = "법인카드 아이디")
    Integer id;
    @Schema(description = "보고서 아이디")
    Integer reportId;
    @Schema(description = "세부내용")
    String description;
    @Schema(description = "작성 시간")
    Timestamp createdAt;
    @Schema(description = "다음 id")
    Integer nextId;

    public static NoteResponse fromDomain(Note note){
        return NoteResponse.builder()
                .id(note.getId())
                .reportId(note.getReportId())
                .description(note.getDescription())
                .createdAt(note.getCreatedAt())
                .nextId(note.getNextId())
                .build();
    }
    public static List<NoteResponse> fromDomain(List<Note> noteList) {
        List<NoteResponse> gameMetaResponseList = new ArrayList<>();
        for(Note i : noteList){
            gameMetaResponseList.add(NoteResponse.fromDomain(i));
        }
        return gameMetaResponseList;
    }
}
