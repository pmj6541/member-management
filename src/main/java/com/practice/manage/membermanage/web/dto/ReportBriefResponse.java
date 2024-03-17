package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.ReportBrief;
import com.practice.manage.membermanage.main.domain.Summary;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "보고서 응답")
public class ReportBriefResponse {
    @Schema(description = "보고서 아이디")
    Integer id;
    @Schema(description = "보고서 날짜")
    Timestamp date;
    @Schema(description = "보고서 생성 시간")
    Timestamp createdAt;
    @Schema(description = "보고서 최종 수정 시간")
    Timestamp updatedAt;
    @Schema(description = "보고서 제출 시간")
    Timestamp submittedAt;
    @Schema(description = "다음 보고서 id")
    Integer nextId;
    @Schema(description = "보고서 요약")
    Summary summary;
    @Schema(description = "업무일지 리스트")
    List<JournalResponse> journals;

    public static ReportBriefResponse fromDomain(ReportBrief reportBrief){
        return ReportBriefResponse.builder()
                .id(reportBrief.getId())
                .date(reportBrief.getDate())
                .createdAt(reportBrief.getCreatedAt())
                .updatedAt(reportBrief.getUpdatedAt())
                .submittedAt(reportBrief.getSubmittedAt())
                .nextId(reportBrief.getNextId())
                .summary(reportBrief.getSummary())
                .journals(JournalResponse.fromDomain(reportBrief.getJournals()))
                .build();
    }

}
