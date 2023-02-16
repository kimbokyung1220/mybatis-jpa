package com.example.jpa.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestDto {
    // todo: @NotBlank 추가?
    private String userid;
    private String password;
    private String role;
}
