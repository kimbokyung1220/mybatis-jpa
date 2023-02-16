package com.example.jpa.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 암호화, 복호화
     * default: bcrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 암호화된 비밀번호를 반환
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * [WebSecurity]
     * Spring Security Filter에서 무시해야 하는 RequestMatcher 인스턴스 설정 시 사용
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //정적 파일(css, 이미지, ... 등)
    }

    /**
     * [HttpSecurity]
     * 특정 HTTP 요청에 대한 웹 기반 보안 구성 시 사용
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(); //Form 로그인
    }
}