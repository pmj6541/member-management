package com.practice.manage.membermanage.web.dto;

import com.practice.manage.membermanage.auth.service.domain.User;
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
@Schema(description = "사용자 정보 응답")
public class UserResponse {
    @Schema(description = "UID")
    String uid;
    @Schema(description = "이름")
    String displayName;
    @Schema(description = "역할")
    String role;
    @Schema(description = "전화번호")
    String phoneNumber;

    public static UserResponse fromDomain(User user){
        return UserResponse.builder()
                .uid(user.getUid())
                .displayName(user.getDisplayName())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
