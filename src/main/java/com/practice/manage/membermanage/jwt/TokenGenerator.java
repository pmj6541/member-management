package com.practice.manage.membermanage.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.practice.manage.membermanage.auth.repository.AuthMapper;
import com.practice.manage.membermanage.auth.service.domain.Keyset;
import com.practice.manage.membermanage.auth.service.domain.User;
import com.practice.manage.membermanage.auth.service.domain.UserRefresh;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TokenGenerator {
    final AuthMapper authMapper;
    final ShortTokenGenerator shortTokenGenerator;

    @Transactional
    public String generateAccessToken(User user) throws GeneralSecurityException, IOException, JOSEException {
        /* 프로젝트 키 정보 가져오기 */
        Keyset keyset = authMapper.getKeysetByProject("manage");
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + keyset.getAccessTokenTtl() * 1000); // 1시간
        Map<String, Object> payloadClams = new HashMap<>();
        payloadClams.put("uid", user.getUid());
        payloadClams.put("role", user.getRole());
        payloadClams.put("iat", iat.getTime());
        payloadClams.put("exp", exp.getTime());

        JWSObject jwsObject = new JWSObject(
                new JWSHeader.Builder(JWSAlgorithm.RS256)
                        .type(JOSEObjectType.JWT)
                        .keyID(keyset.getProject()).build(),
                new Payload(payloadClams));
        jwsObject.sign(getSigner(keyset.getPrivateKey()));
        return jwsObject.serialize();
    }

    @Transactional
    public String generateRefreshToken(User user) {
        Keyset keyset = authMapper.getKeysetByProject("manage");
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + keyset.getRefreshTokenTtl() * 1000); // 1시간
        String token = shortTokenGenerator.generate();
        UserRefresh userRefresh = UserRefresh.builder()
                .uid(user.getUid())
                .refreshToken(token)
                .issuedAt(iat.getTime())
                .expiredAt(exp.getTime())
                .build();
        authMapper.setRefreshToken(userRefresh);
        return token;
    }


    public JWSSigner getSigner(String privateKeyBase64) throws GeneralSecurityException, IOException {

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        RSAPrivateKey privateKey = RsaHelper.readPrivateKey(privateKeyBytes);

        return new RSASSASigner(privateKey);
    }
}
