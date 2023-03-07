package com.example.community.community.service;

import com.example.community.community.dto.ListDto;
import com.example.community.community.entity.Posting;
import com.example.community.community.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
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
                .orElseThrow(() -> new IllegalArgumentException("Post doesn't exist"));

        findPosting.setTitle(posting.getTitle());
        findPosting.setContent(posting.getContent());

        postingRepository.save(findPosting);

        return true;
    }
    @Override
    public List<ListDto> list() {
        List<Posting> postingList = postingRepository.findAll();

        List<ListDto> listDtoList = new ArrayList<>();


        for (Posting x : postingList){
            listDtoList.add(ListDto.builder()
                    .postNum(x.getPostNum())
                    .title(x.getTitle())
                    .userId(x.getUserId())
                    .createdAt(x.getCreatedAt())
                    .build());
        }

        return listDtoList;
    }

    @Override
    public Posting detail(long postNum) {
        Optional<Posting> posting = postingRepository.findById(postNum);

        if(!posting.isPresent()){
            return null;
        }

        return posting.get();
    }

}
