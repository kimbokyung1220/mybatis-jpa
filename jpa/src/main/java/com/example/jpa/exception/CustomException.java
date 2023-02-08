package com.example.jpa.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 의도한 에외상황에 사용하기 위해 공통 Exception을 구현
 * ex) throw new CustomException(ErrorCode.ENUM_CODE);
 */
@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private ErrorCode errorCode;

    public CustomException(ErrorCode e) {
        super(e.getMessage());
        this.errorCode = e;
    }
}
