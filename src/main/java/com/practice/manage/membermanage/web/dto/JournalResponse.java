package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Journal;
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
@Schema(description = "업무일지 응답")
public class JournalResponse {
    @Schema(description = "업무일지 아이디")
    Integer id;
    @Schema(description = "방문 시간")
    Timestamp visitedAt;
    @Schema(description = "장소명")
    String placeName;
    @Schema(description = "세부내용")
    String description;
    @Schema(description = "메모")
    String memo;
    @Schema(description = "작성 시간")
    Timestamp createdAt;
    @Schema(description = "다음 id")
    Integer nextId;

    public static JournalResponse fromDomain(Journal journal){
        return JournalResponse.builder()
                .id(journal.getId())
                .visitedAt(journal.getVisitedAt())
                .placeName(journal.getPlaceName())
                .description(journal.getDescription())
                .memo(journal.getMemo())
                .createdAt(journal.getCreatedAt())
                .nextId(journal.getNextId())
                .build();
    }
    public static List<JournalResponse> fromDomain(List<Journal> journalList) {
        List<JournalResponse> gameMetaResponseList = new ArrayList<>();
        for(Journal i : journalList){
            gameMetaResponseList.add(JournalResponse.fromDomain(i));
        }
        return gameMetaResponseList;
    }
}
