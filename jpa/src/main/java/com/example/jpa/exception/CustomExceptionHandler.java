package com.example.jpa.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;


/**
 * Exception handler 구현
 * @RestControllerAdvice를 활용하여 공통된 오류 메시지 형식으로 응답하도록 구현
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final CustomException e) {
        //e.printStackTrace();
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ErrorResponse.builder()
                        .code(e.getErrorCode().getCode())
                        .desc(e.getErrorCode().getMessage())
                        .status(e.getErrorCode().getStatus())
                        .build());
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final InvalidFormatException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ErrorCode.INVALID_PARAMETER.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.INVALID_PARAMETER.getCode())
                        .desc(ErrorCode.INVALID_PARAMETER.getMessage())
                        .status(ErrorCode.INVALID_PARAMETER.getStatus())
                        .build());
    }

    // [Exception] API 호출 시 '객체' 혹은 '파라미터' 데이터 값이 유효하지 않은 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object processValidationError(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(ErrorCode.TYPE_NULL_ERROR.getStatus())
                        .body(ErrorResponse.builder()
                                .code(ErrorCode.TYPE_NULL_ERROR.getCode())
                                .desc(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                                .build());
    }

    // [Exception] 클라이언트에서 Body로 '객체' 데이터가 넘어오지 않았을 경우
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object processValidationError(HttpMessageNotReadableException e) {
        return ResponseEntity
                .status(ErrorCode.TYPE_NULL_ERROR.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.TYPE_NULL_ERROR.getCode())
                        .desc(ErrorCode.TYPE_NULL_ERROR.getMessage())
                        .build());
    }

    // [Exception] 클라이언트에서 잘못된 메서드로 요청했을 경우ㅌ
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object processValidationError(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity
                .status(ErrorCode.METHOD_ERROR.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.METHOD_ERROR.getCode())
                        .desc(ErrorCode.METHOD_ERROR.getMessage())
                        .status(ErrorCode.METHOD_ERROR.getStatus())
                        .build());
    }

    // [Exception] 잘못된 서버 요청일 경우 발생한 경우
    @ExceptionHandler(HttpClientErrorException.class)
    public Object processValidationError(HttpClientErrorException e) {
        return ResponseEntity
                .status(ErrorCode.HTTP_CT_ERROR.getStatus())
                .body(ErrorResponse.builder()
                        .code(ErrorCode.HTTP_CT_ERROR.getCode())
                        .desc(e.getMessage())
                        .status(ErrorCode.HTTP_CT_ERROR.getStatus())
                        .build());
    }
}
