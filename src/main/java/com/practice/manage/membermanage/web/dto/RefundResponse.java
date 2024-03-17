package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.main.domain.Refund;
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
@Schema(description = "반품내역 응답")
public class RefundResponse {
    @Schema(description = "반품내역 아이디")
    Integer id;
    @Schema(description = "장소명")
    String placeName;
    @Schema(description = "개수")
    Integer quantity;
    @Schema(description = "제품명")
    String productName;
    @Schema(description = "단위")
    String unit;
    @Schema(description = "반품 사유")
    String reason;
    @Schema(description = "작성 시간")
    Timestamp createdAt;
    @Schema(description = "다음 id")
    Integer nextId;

    public static RefundResponse fromDomain(Refund refund){
        return RefundResponse.builder()
                .id(refund.getId())
                .placeName(refund.getPlaceName())
                .quantity(refund.getQuantity())
                .productName(refund.getProductName())
                .unit(refund.getUnit())
                .reason(refund.getReason())
                .createdAt(refund.getCreatedAt())
                .nextId(refund.getNextId())
                .build();
    }
    public static List<RefundResponse> fromDomain(List<Refund> refundList) {
        List<RefundResponse> gameMetaResponseList = new ArrayList<>();
        for(Refund i : refundList){
            gameMetaResponseList.add(RefundResponse.fromDomain(i));
        }
        return gameMetaResponseList;
    }
}
