package com.example.jpa.repository;

import com.example.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    //boolean existsByUserid(String userid);

    Optional<Object> findByUserid(String userid);
}
