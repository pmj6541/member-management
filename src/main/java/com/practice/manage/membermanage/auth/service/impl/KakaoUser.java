package com.practice.manage.membermanage.auth.service.impl;

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
