package com.practice.manage.membermanage.main.domain;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReportBrief {
    Integer id;
    Timestamp date;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp submittedAt;
    Integer nextId;
    Summary summary;
    List<Journal> journals;
}

