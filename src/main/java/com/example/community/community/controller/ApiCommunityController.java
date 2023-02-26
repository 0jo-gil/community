package com.example.community.community.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCommunityController {
    @PostMapping("/api/community/write")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body("저장 성공");
    }


}
