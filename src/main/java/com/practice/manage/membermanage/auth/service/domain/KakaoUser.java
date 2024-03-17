package com.practice.manage.membermanage.auth.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KakaoUser {
    String uid;
    String phoneNumber;
    String displayName;
}
