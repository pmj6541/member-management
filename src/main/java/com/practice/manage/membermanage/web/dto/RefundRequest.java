package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Refund;
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
@Schema(description = "반품내역 요청")
public class RefundRequest {
    @Schema(description = "Report의 ID")
    Integer reportId;
    @Schema(description = "장소명")
    String placeName;
    @Schema(description = "개수")
    Integer quantity;
    @Schema(description = "제품")
    String productName;
    @Schema(description = "용량")
    String unit;
    @Schema(description = "반품 사유")
    String reason;
    @Schema(description = "메모")
    String memo;


    public static Refund fromDto(RefundRequest refundRequest){
        return Refund.builder()
                .reportId(refundRequest.getReportId())
                .placeName(refundRequest.getPlaceName())
                .quantity(refundRequest.getQuantity())
                .productName(refundRequest.getProductName())
                .unit(refundRequest.getUnit())
                .reason(refundRequest.getReason())
                .memo(refundRequest.getMemo())
                .build();
    }
}
