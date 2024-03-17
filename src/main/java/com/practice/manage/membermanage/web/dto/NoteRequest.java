package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Note;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "기타사항 요청")
public class NoteRequest {
    @Schema(description = "Report의 ID")
    Integer reportId;
    @Schema(description = "세부내용")
    String description;

    public static Note fromDto(NoteRequest noteRequest){
        return Note.builder()
                .reportId(noteRequest.getReportId())
                .description(noteRequest.getDescription())
                .build();
    }
}
