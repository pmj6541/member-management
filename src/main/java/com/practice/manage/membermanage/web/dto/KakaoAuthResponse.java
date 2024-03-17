package com.practice.manage.membermanage.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "카카오 API 응답")
public class KakaoAuthResponse {
    @Schema(description = "Kakao Access Token")
    String access_token;
    @Schema(description = "토큰 타입")
    String token_type;
    @Schema(description = "Refresh Token")
    String refresh_token;
    @Schema(description = "유저 정보 포함 jwt")
    String id_token;

}
