package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Summary;
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
@Schema(description = "보고서 요약")
public class SummaryResponse {
    @Schema(description = "일지 총 개수")
    Integer journalCount;
    @Schema(description = "반품 총 개수")
    Integer refundCount;
    @Schema(description = "법인카드 사용 금액 총합")
    Integer expenseAmount;
    @Schema(description = "법인카드 사용 총 횟수")
    Integer expenseCount;
    @Schema(description = "기타사항 총 개수")
    Integer noteCount;

    public static SummaryResponse fromDomain(Summary summary) {
        return SummaryResponse.builder()
                .journalCount(summary.getJournalCount())
                .refundCount(summary.getRefundCount())
                .expenseAmount(summary.getExpenseAmount())
                .expenseCount(summary.getExpenseCount())
                .noteCount(summary.getNoteCount())
                .build();
    }

}
