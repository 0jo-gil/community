package com.example.community.member.repository;

import com.example.community.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
}
