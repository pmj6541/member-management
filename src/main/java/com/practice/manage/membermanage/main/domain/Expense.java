package com.practice.manage.membermanage.main.domain;

import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Expense {
    Integer id;
    Integer reportId;
    Long amount;
    String type;
    String description;
    Timestamp createdAt;
    Integer nextId;
}
