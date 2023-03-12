package com.example.community.community.service;

import com.example.community.community.dto.PostDto;
import com.example.community.community.entity.Posting;
import com.example.community.community.model.PostingParam;
import org.springframework.data.domain.Page;

public interface PostingService {

    /**
     * 글쓰기 
     */
    boolean register(Posting posting);

    /**
     * 리스트 조회
     */
    Page<PostDto> list(PostingParam parameter);

    /**
     * 게시글 리스트 상세 조회
     */
    PostDto detail(long postNum);

    /**
     * 게시글 수정
     */
    boolean modify(long postNum, Posting posting);
}
