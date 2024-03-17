package com.practice.manage.membermanage.auth.service;

import com.nimbusds.jose.JOSEException;
import com.practice.manage.membermanage.auth.exception.InvalidKakaoLoginException;
import com.practice.manage.membermanage.auth.exception.TokenIsNotValidException;
import com.practice.manage.membermanage.auth.exception.UserNotFoundException;
import com.practice.manage.membermanage.auth.service.domain.Signin;
import com.practice.manage.membermanage.auth.service.domain.User;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface AuthService {
    Signin getKakaoSignin(String code) throws InvalidKakaoLoginException, UserNotFoundException, GeneralSecurityException, IOException, JOSEException;

    Signin refreshSignin(String refreshToken) throws TokenIsNotValidException, GeneralSecurityException, IOException, JOSEException;

    User getUser(String uid);
}
