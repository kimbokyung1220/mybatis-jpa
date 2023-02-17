package com.example.jpa.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 암호화, 복호화
     * default: bcrypt
     */

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
//        List<String> asd = new ArrayList<>();
//        asd.add("http://localhost:8080");//서버에서 -> 프론트  //프론트 -> 서버
//        asd.add("localhost:8080");
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedOrigins(asd);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.addExposedHeader("X-ACCESS-TOKEN");
        configuration.addExposedHeader("X-REFRESH-TOKEN");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

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
        http
                .headers().frameOptions().disable(); //h2-console화면을 사용하기 위해 해당 옵션을 disable처리

        http.csrf().disable().authorizeRequests()
               // .antMatchers("/common/login").permitAll()
                .antMatchers("/join").permitAll()
                .and()
                // 로그인기능
                .formLogin()
                .passwordParameter("password")
                .usernameParameter("userid")
//                .loginPage("/login")
                .loginProcessingUrl("/common/login")
//                .failureUrl("/login?error")
        ;
    }
}