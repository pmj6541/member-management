package com.practice.manage.membermanage.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableConfigurationProperties(MemberManageProperties.class)
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@Configuration
public class MemberManageConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CORS 세팅
        http.cors();

        return http
                .csrf().disable()
                .headers().frameOptions().disable().and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                //.requestMatchers("/**").permitAll()
                .anyRequest().authenticated().and().build();
    }


}
