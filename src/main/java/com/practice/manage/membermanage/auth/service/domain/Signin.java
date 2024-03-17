package com.practice.manage.membermanage.auth.service.domain;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Signin {
    String accessToken;
    String refreshToken;
    String displayName;
    String role;
}
