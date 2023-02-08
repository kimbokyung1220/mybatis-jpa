package com.example.jpa.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_PARAMETER("CER-01", HttpStatus.BAD_REQUEST, "값을 다시 확인해 주시기 바랍니다."),
    RESOURCE_NOT_FOUND("CER-02", HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR("CER-03", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    METHOD_ERROR("CER-04", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"), //HttpMessageNotReadableException
    SS_SS("CER-03", HttpStatus.INTERNAL_SERVER_ERROR),
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
