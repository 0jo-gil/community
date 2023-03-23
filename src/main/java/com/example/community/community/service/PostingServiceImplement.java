package com.example.community.community.service;

import com.example.community.community.dto.PostDto;
import com.example.community.community.entity.Posting;
import com.example.community.community.exception.PostingExistException;
import com.example.community.community.model.PostingParam;
import com.example.community.community.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Service
public class PostingServiceImplement implements PostingService{
    private final PostingRepository postingRepository;
    @Override
    public boolean register(Posting posting) {
        try{
            postingRepository.save(posting);
        } catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(long postNum, Posting posting) {
        Posting findPosting = postingRepository.findById(postNum)
                .orElseThrow(() -> new PostingExistException());

        findPosting.setTitle(posting.getTitle());
        findPosting.setContent(posting.getContent());

        postingRepository.save(findPosting);

        return true;
    }
    @Override
    public Page<PostDto> list(PostingParam parameter) {
        Pageable pageable = PageRequest.of(
                parameter.getPageIndex() - 1,
                10,
                Sort.by("postNum").descending());

        Page<Posting> postingList = postingRepository.findAll(pageable);
        List<PostDto> listDtoPost = new ArrayList<>();

        for (Posting posting : postingList){
            listDtoPost.add(PostDto.of(posting));
        }

        return new PageImpl<>(listDtoPost, pageable, postingList.getTotalElements());
    }

    @Override
    public PostDto detail(long postNum) {
        Posting posting = postingRepository
                    .findById(postNum)
                    .orElseThrow(() -> new PostingExistException()
                );

        return PostDto.of(posting);
    }

}
