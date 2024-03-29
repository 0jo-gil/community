package com.example.community.community.controller;

import com.example.community.community.service.PostingService;
import com.example.community.community.entity.Posting;
import com.example.community.community.model.PostingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class ApiCommunityController extends BaseController {
    private final PostingService postingService;
    @PostMapping("/api/community/write")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity writeSubmit(
            @RequestBody PostingDto parameter,
            Principal principal
    ){
        HttpHeaders headers = new HttpHeaders();

        String userId = principal.getName();
        Posting posting = Posting.builder()
                .title(parameter.getTitle())
                .content(parameter.getContent())
                .userId(userId)
                .viewCount(0)
                .likeCount(0)
                .createdAt(LocalDateTime.now())
                .lastUpdatedAt(LocalDateTime.now())
                .notMemberShowYn(false)
                .build();

        boolean result = postingService.register(posting);

        headers.setLocation(URI.create(
                String.format("/community/detail?id=%s", posting.getPostNum())
        ));

        if(!result) {
            return ResponseEntity.badRequest().body("글쓰기 실패");
        }

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @PutMapping("/api/community/modify")
    public ResponseEntity<?> modify(
            @RequestBody PostingDto parameter
    ) {
        return ResponseEntity.ok().body("");
    }


}
