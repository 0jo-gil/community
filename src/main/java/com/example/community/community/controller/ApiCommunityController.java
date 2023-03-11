package com.example.community.community.controller;

import com.example.community.community.service.PostingService;
import com.example.community.community.entity.Posting;
import com.example.community.community.model.PostingInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class ApiCommunityController {
    private final PostingService postingService;

    @PostMapping("/api/community/write")
    public ResponseEntity<?> list(
            @RequestBody PostingInput parameter,
            Principal principal
            ){
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

        System.out.println(result);

        if(!result) {
            return ResponseEntity.badRequest().body("글쓰기 실패");
        }
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/api/community/modify")
    public ResponseEntity<?> modify(
            @RequestBody PostingInput parameter
    ) {



        return ResponseEntity.ok().body("");
    }


}
