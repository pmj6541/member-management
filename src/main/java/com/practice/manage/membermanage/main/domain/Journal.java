package com.practice.manage.membermanage.main.domain;

import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Journal {
    Integer id;
    Integer reportId;
    Timestamp visitedAt;
    String placeName;
    String description;
    String memo;
    Timestamp createdAt;
    Integer nextId;
}
