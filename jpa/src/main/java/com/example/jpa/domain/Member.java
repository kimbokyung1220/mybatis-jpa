package com.example.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userid;
    @Column(nullable = false)
    private String password;
    private String role;
    
    // 로그인 시, 비밀번호와 암호화된 비밀번호가 일치하는지
    public void encodePasswordCheck(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(this.password);
    }


}
