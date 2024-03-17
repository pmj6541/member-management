package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.auth.service.domain.Signin;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SigninResponse {
    @Schema(description = "액세스 토큰")
    String accessToken;
    @Schema(description = "리프레쉬 토큰")
    String refreshToken;
    @Schema(description = "사용자 실명")
    String displayName;
    @Schema(description = "사용자 역할")
    String role;

    public static SigninResponse fromDomain(Signin signin) {
        return SigninResponse.builder()
                .accessToken(signin.getAccessToken())
                .refreshToken(signin.getRefreshToken())
                .displayName(signin.getDisplayName())
                .role(signin.getRole())
                .build();
    }
}
