package com.example.community.community.service;

import com.example.community.community.dto.ListDto;
import com.example.community.community.entity.Posting;

import java.util.List;

public interface PostingService {

    /**
     * 글쓰기 
     */
    boolean register(Posting posting);

    /**
     * 리스트 조회
     */
    List<ListDto> list();

    /**
     * 게시글 리스트 상세 조회
     */
    Posting detail(long postNum);

    /**
     * 게시글 수정
     */
    boolean modify(long postNum, Posting posting);
}
