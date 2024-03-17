package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Journal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "업무일지 요청")
public class JournalRequest {
    @Schema(description = "Report의 ID")
    Integer reportId;
    @Schema(description = "방문 시간")
    Timestamp visitedAt;
    @Schema(description = "장소명")
    String placeName;
    @Schema(description = "세부내용")
    String description;
    @Schema(description = "작성 시간")
    Timestamp createdAt;
    @Schema(description = "다음 id")
    Integer nextJournalId;

    public static Journal fromDto(JournalRequest journalRequest){
        return Journal.builder()
                .reportId(journalRequest.getReportId())
                .visitedAt(journalRequest.getVisitedAt())
                .placeName(journalRequest.getPlaceName())
                .description(journalRequest.getDescription())
                .createdAt(journalRequest.getCreatedAt())
                .nextId(journalRequest.getNextJournalId())
                .build();
    }
}
