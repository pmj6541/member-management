package com.practice.manage.membermanage.exception;

import com.practice.manage.membermanage.auth.exception.InvalidKakaoLoginException;
import com.practice.manage.membermanage.auth.exception.TokenIsNotValidException;
import com.practice.manage.membermanage.auth.exception.UserNotFoundException;
import com.practice.manage.membermanage.exception.code.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidKakaoLoginException.class)
    private ResponseEntity<ErrorCode> InvalidKakaoLoginException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorCode.builder()
                .name("카카오 로그인 에러")
                .message("카카오 로그인에 실패하였습니다.")
                .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ErrorCode> UserNotFoundException() {
        return ResponseEntity.internalServerError().body(ErrorCode.builder()
                .name("사용자 조회 에러")
                .message("해당 코드의 사용자가 조회되지 않습니다.")
                .build());
    }

    @ExceptionHandler(TokenIsNotValidException.class)
    private ResponseEntity<ErrorCode> TokenExpiredException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorCode.builder()
                .name("Token 유효성 검사 실패")
                .message("토큰이 유효하지 않습니다.")
                .build());
    }

}
