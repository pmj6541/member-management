package com.practice.manage.membermanage.auth.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.practice.manage.membermanage.auth.exception.InvalidKakaoLoginException;
import com.practice.manage.membermanage.auth.exception.TokenIsNotValidException;
import com.practice.manage.membermanage.auth.exception.UserNotFoundException;
import com.practice.manage.membermanage.auth.repository.AuthMapper;
import com.practice.manage.membermanage.auth.service.domain.KakaoUser;
import com.practice.manage.membermanage.auth.service.domain.Signin;
import com.practice.manage.membermanage.auth.service.AuthService;
import com.practice.manage.membermanage.auth.service.domain.Status;
import com.practice.manage.membermanage.auth.service.domain.User;
import com.practice.manage.membermanage.jwt.TokenGenerator;
import com.practice.manage.membermanage.web.dto.KakaoAuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    final TokenGenerator tokenGenerator;
    final ObjectMapper objectMapper;

    @Value("${manage.api.key}")
    private String apiKey;

    @Value("${manage.api.url}")
    private String apiUrl;

    final AuthMapper authMapper;

    @Override
    public Signin getKakaoSignin(String code) throws InvalidKakaoLoginException, UserNotFoundException, GeneralSecurityException, IOException, JOSEException {
        /* 카카오 코드 유효성 검사 */
        KakaoUser kakaoUser = authorizeKakao(code);
        /* 서비스 등록된 사용자 확인 */
        User user = authMapper.getUserByUid(kakaoUser.getUid());
        if(user == null){
            authMapper.addUser(kakaoUser.getUid(), kakaoUser.getDisplayName(), kakaoUser.getPhoneNumber(), Status.not_allowed);
            authMapper.setUserCodeByUid(kakaoUser.getUid(), code);
            throw new UserNotFoundException();
        }
        authMapper.setUserCodeByUid(kakaoUser.getUid(), code);
        /* 검증이 끝난 유저에 대해 accessToken(Signin 객체 발급) */
        String accessToken = tokenGenerator.generateAccessToken(user);
        /* Refresh Token 생성 및 등록 */
        String refreshToken = tokenGenerator.generateRefreshToken(user);

        return Signin.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .displayName(user.getDisplayName())
                .role(user.getRole())
                .build();
    }

    @Override
    public Signin refreshSignin(String refreshToken) throws TokenIsNotValidException, GeneralSecurityException, IOException, JOSEException {
        /* Refresh Token 유효성 검사 */
        Date now = new Date();
        String uid = authMapper.getUidByRefreshTokenAndTime(refreshToken, now.getTime());
        User user = authMapper.getUserByUid(uid);
        if(user == null){
            throw new TokenIsNotValidException();
        }
        /* Access Token 재발급 */
        String accessToken = tokenGenerator.generateAccessToken(user);
        return Signin.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .displayName(user.getDisplayName())
                .role(user.getRole())
                .build();
    }

    @Override
    public User getUser(String uid) {
        return authMapper.getUserByUid(uid);
    }

    private KakaoUser authorizeKakao(String code) throws InvalidKakaoLoginException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", apiKey);
        params.add("redirect_uri", apiUrl);
        params.add("code", code);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<>(params, headers);

        try{/* 카카오 Access Token 발급 요청 */
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    kakaoTokenReq,
                    String.class
            );
            KakaoAuthResponse kakaoAuthResponse = objectMapper.readValue(response.getBody(), KakaoAuthResponse.class);
            log.info(kakaoAuthResponse.toString());
            JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation("https://kauth.kakao.com");
            Jwt jwt = jwtDecoder.decode(kakaoAuthResponse.getId_token());
            return KakaoUser.builder()
                    .uid(jwt.getSubject())
                    .displayName(jwt.getClaim("nickname"))
                    .phoneNumber("dev_mod")
                    .build();
        }catch(Exception e){
            throw new InvalidKakaoLoginException();
        }
    }
}
