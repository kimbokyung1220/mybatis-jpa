package com.example.jpa.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private T data;
    private String massege;

    public static <T> ResponseDto<T> success(T data, String massege) {
        return new ResponseDto<>(true, data, massege);
    }
    public static <T> ResponseDto<T> fail(T error, String massege) {
        return new ResponseDto<>(false, null, massege);
    }
}

