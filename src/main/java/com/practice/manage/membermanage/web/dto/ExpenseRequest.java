package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Expense;
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
@Schema(description = "법인카드 요청")
public class ExpenseRequest {
    @Schema(description = "Report의 ID")
    Integer reportId;
    @Schema(description = "금액")
    Long amount;
    @Schema(description = "종류")
    String type;
    @Schema(description = "세부내용")
    String description;

    public static Expense fromDto(ExpenseRequest expenseRequest){
        return Expense.builder()
                .reportId(expenseRequest.getReportId())
                .amount(expenseRequest.getAmount())
                .type(expenseRequest.getType())
                .description(expenseRequest.getDescription())
                .build();
    }
}
