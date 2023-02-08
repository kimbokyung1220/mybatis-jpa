package com.example.jpa.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;


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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object processValidationError(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(ErrorCode.INVALID_PARAMETER.getStatus())
                        .body(ErrorResponse.builder()
                                .code("sdf")
                                .desc(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                                .build());
    }

}
