package com.practice.manage.membermanage.main.domain;

import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Refund {
    Integer id;
    Integer reportId;
    String placeName;
    Integer quantity;
    String productName;
    String unit;
    String reason;
    String memo;
    Timestamp createdAt;
    Integer nextId;
}
