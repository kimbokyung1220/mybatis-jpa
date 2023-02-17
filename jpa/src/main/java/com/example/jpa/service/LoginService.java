package com.example.jpa.service;

import com.example.jpa.domain.Member;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.request.LoginRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@AllArgsConstructor
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public ResponseEntity<?> loginMember(LoginRequestDto requestDto, HttpServletResponse response) {

        return null;
    }

    public ResponseEntity<?> joinMember(LoginRequestDto requestDto) {
        String passwordEncode = passwordEncoder.encode(requestDto.getPassword());

        Member member = Member.builder()
                .userid(requestDto.getUserid())
                .password(passwordEncode)
                .role(requestDto.getRole())
                .build();

        memberRepository.save(member);

        return ResponseEntity.ok().body(member);
    }
}
