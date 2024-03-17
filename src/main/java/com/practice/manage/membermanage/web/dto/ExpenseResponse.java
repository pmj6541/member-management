package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Expense;
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
@Schema(description = "법인카드 응답")
public class ExpenseResponse {
    @Schema(description = "법인카드 아이디")
    Integer id;
    @Schema(description = "금액")
    Long amount;
    @Schema(description = "종류")
    String type;
    @Schema(description = "세부내용")
    String description;
    @Schema(description = "작성 시간")
    Timestamp createdAt;
    @Schema(description = "다음 id")
    Integer nextId;

    public static ExpenseResponse fromDomain(Expense expense){
        return ExpenseResponse.builder()
                .id(expense.getId())
                .amount(expense.getAmount())
                .type(expense.getType())
                .description(expense.getDescription())
                .createdAt(expense.getCreatedAt())
                .nextId(expense.getNextId())
                .build();
    }
    public static List<ExpenseResponse> fromDomain(List<Expense> ExpenseList) {
        List<ExpenseResponse> gameMetaResponseList = new ArrayList<>();
        for(Expense i : ExpenseList){
            gameMetaResponseList.add(ExpenseResponse.fromDomain(i));
        }
        return gameMetaResponseList;
    }
}
