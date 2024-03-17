package com.practice.manage.membermanage.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        servers = {@Server(url = "/v1")},
        info = @Info(title = "member-manage", version = "0.1", description = "업무관리"))
@SecuritySchemes({
        @SecurityScheme(
                name = ApiDocConfig.SECURITY_SCHEME_BEARER,
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer",
                description = "인증이 필요한 경우 사용될 token 을 넣어준다."
        )
})
public class ApiDocConfig {

    public static final String SECURITY_SCHEME_BEARER = "bearerAuth";

}