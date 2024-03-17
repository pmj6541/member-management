package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Report;
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
@Schema(description = "보고서 응답")
public class ReportResponse {
    @Schema(description = "보고서 아이디")
    Integer id;
    @Schema(description = "보고서 날짜")
    Timestamp date;
    @Schema(description = "보고서 생성 시간")
    Timestamp createdAt;
    @Schema(description = "보고서 최종 수정 시간")
    Timestamp updatedAt;
    @Schema(description = "보고서 상태")
    ReportStatus status;
    @Schema(description = "보고서 제출 시간")
    Timestamp submittedAt;
    @Schema(description = "다음 id")
    Integer nextId;
    @Schema(description = "보고서 요약")
    SummaryResponse summary;
    @Schema(description = "업무일지 리스트")
    List<JournalResponse> journals;


    public static ReportResponse fromDomain(Report report){
        return ReportResponse.builder()
                .id(report.getId())
                .date(report.getDate())
                .createdAt(report.getCreatedAt())
                .updatedAt(report.getUpdatedAt())
                .status(report.getStatus())
                .submittedAt(report.getSubmittedAt())
                .nextId(report.getNextId())
                .summary(SummaryResponse.fromDomain(report.getSummary()))
                .journals(JournalResponse.fromDomain(report.getJournals()))
                .build();
    }

    public static List<ReportResponse> fromDomain(List<Report> reportList){
        List<ReportResponse> reportResponseList = new ArrayList<>();
        for(Report i : reportList){
            reportResponseList.add(ReportResponse.fromDomain(i));
        }
        return reportResponseList;
    }


}
