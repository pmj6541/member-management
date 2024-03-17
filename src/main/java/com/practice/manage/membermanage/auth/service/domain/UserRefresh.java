package com.practice.manage.membermanage.auth.service.domain;

import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRefresh {
    Integer id;
    String uid;
    String refreshToken;
    Long issuedAt;
    Long expiredAt;
    Timestamp insDate;
}
