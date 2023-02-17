package com.example.jpa.controller;

import com.example.jpa.request.LoginRequestDto;
import com.example.jpa.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = "/login")
    public ResponseEntity<?> loginMember(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        return loginService.loginMember(requestDto, response);
    }

    @PostMapping(value = "/join")
    public ResponseEntity<?> joinMember(@RequestBody LoginRequestDto loginRequestDto){
        return loginService.joinMember(loginRequestDto);
    }
}
