package com.practice.manage.membermanage.web;

import com.nimbusds.jose.JOSEException;
import com.practice.manage.membermanage.auth.exception.InvalidKakaoLoginException;
import com.practice.manage.membermanage.auth.exception.TokenIsNotValidException;
import com.practice.manage.membermanage.auth.exception.UserNotFoundException;
import com.practice.manage.membermanage.auth.service.AuthService;
import com.practice.manage.membermanage.auth.service.domain.Signin;
import com.practice.manage.membermanage.auth.service.domain.User;
import com.practice.manage.membermanage.web.dto.SigninResponse;
import com.practice.manage.membermanage.web.dto.UserResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.practice.manage.membermanage.config.ApiDocConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "인증", description = "인증 API")
@Slf4j
public class AuthController {

    final AuthService authService;

    @GetMapping("/kakao")
    public ResponseEntity<SigninResponse> signIn(
            @RequestParam String code
    ) throws UserNotFoundException, InvalidKakaoLoginException, GeneralSecurityException, IOException, JOSEException {
        Signin signin = authService.getKakaoSignin(code);
        return ResponseEntity.ok().body(SigninResponse.fromDomain(signin));
    }

    @GetMapping("/refresh")
    public ResponseEntity<SigninResponse> refreshToken(
            @RequestParam String refreshToken
    ) throws GeneralSecurityException, IOException, JOSEException, TokenIsNotValidException {
        Signin signin = authService.refreshSignin(refreshToken);
        return ResponseEntity.ok().body(SigninResponse.fromDomain(signin));
    }

    @GetMapping("/me")
    @SecurityRequirement(name = ApiDocConfig.SECURITY_SCHEME_BEARER)
    public ResponseEntity<UserResponse> getUser(
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");

        User user = authService.getUser(uid);
        return ResponseEntity.ok().body(UserResponse.fromDomain(user));
    }


}
