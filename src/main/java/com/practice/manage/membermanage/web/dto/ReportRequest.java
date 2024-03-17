package com.practice.manage.membermanage.web.dto;

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
@Schema(description = "보고서 생성 요청")
public class ReportRequest {
    @Schema(description = "보고서 날짜")
    Timestamp date;
}
