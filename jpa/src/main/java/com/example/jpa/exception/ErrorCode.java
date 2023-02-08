package com.example.jpa.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_PARAMETER("CER-01", HttpStatus.BAD_REQUEST, "잘못된 값입니다."),
    RESOURCE_NOT_FOUND("CER-02", HttpStatus.NOT_FOUND, "존재하지 않는 Resource입니다."),
    INTERNAL_SERVER_ERROR("CER-03", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error 입니다."),
    METHOD_ERROR("CER-04", HttpStatus.INTERNAL_SERVER_ERROR, "잘못된 메서드 요청입니다."),
    TYPE_NULL_ERROR("CER-05", HttpStatus.BAD_REQUEST,"모든 값을 입력해 주세요."),
    HTTP_CT_ERROR("CER-06", HttpStatus.INTERNAL_SERVER_ERROR, " 잘못된 서버 요청입니다."),
    ;

    private final String code;
    private final HttpStatus status;
    private String message;

    ErrorCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    ErrorCode(String code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }
}
