package com.practice.manage.membermanage.exception.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class ErrorCode {

    String name;
    String message;

}
