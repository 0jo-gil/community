package com.example.community.community.repository;

import com.example.community.community.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;


// 게시글 대용량 트래픽 환경 캐싱 - https://junshock5.tistory.com/105, https://nowonbun.tistory.com/309
public interface PostingRepository extends JpaRepository<Posting, Long> {
}
