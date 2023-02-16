package com.example.jpa.request.user;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@Builder
public class LoginRequestDto {
    // todo: @NotBlank 추가?
    private String userid;
    private String password;
    private String role;
}
