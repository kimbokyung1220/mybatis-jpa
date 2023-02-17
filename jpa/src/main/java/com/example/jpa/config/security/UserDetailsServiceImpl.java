package com.example.jpa.config.security;

import com.example.jpa.domain.Member;
import com.example.jpa.exception.ErrorCode;
import com.example.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // @Service를 해줘야 IOC가능 => loadUserByUsername 오버라이드 가능
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        if (userid == null || userid.equals("")) {
            throw new UsernameNotFoundException(userid);
        }

        Member member = (Member) memberRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.NOT_FOUND_USERID.getMessage()));

        return new UserDetailsImpl(member);
    }
}