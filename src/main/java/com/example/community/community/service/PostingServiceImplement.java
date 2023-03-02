package com.example.community.community.service;

import com.example.community.community.dto.ListDto;
import com.example.community.community.entity.Posting;
import com.example.community.community.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostingServiceImplement implements PostingService{
    private final PostingRepository postingRepository;
    private final List<ListDto> listDtoList;
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
    public List<ListDto> list() {
        List<Posting> postingList = postingRepository.findAll();

        for (Posting x : postingList){
            listDtoList.add(ListDto.builder()
                    .title( x.getTitle())
                    .userId(x.getUserId())
                    .createdAt(x.getCreatedAt())
                    .build());
        }

        return listDtoList;
    }
}
