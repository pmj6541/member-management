package com.practice.manage.membermanage.main.domain;

import com.practice.manage.membermanage.web.dto.ReportStatus;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Report {
    Integer id;
    Timestamp date;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp submittedAt;
    ReportStatus status;
    Integer nextId;
    Summary summary;

    List<Journal> journals;
}

