package com.practice.manage.membermanage.auth.service.domain;

import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    Integer id;
    Timestamp insDate;
    Timestamp modDate;
    String uid;
    String displayName;
    String authCode;
    String role;
    String phoneNumber;
}
