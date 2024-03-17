package com.practice.manage.membermanage.auth.service.domain;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Keyset {
    Integer id;
    String project;
    String privateKey;
    String publicKey;
    Long accessTokenTtl;
    Long refreshTokenTtl;

}
