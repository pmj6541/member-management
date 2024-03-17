package com.practice.manage.membermanage.main.domain;

import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Note {
    Integer id;
    Integer reportId;
    String description;
    Timestamp createdAt;
    Integer nextId;
}
