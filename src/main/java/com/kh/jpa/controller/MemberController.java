package com.kh.jpa.controller;

import com.kh.jpa.dto.MemberDto;
import com.kh.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원등록API
    @PostMapping
    public ResponseEntity<String> addMember(@RequestBody MemberDto.Create createDto) {
        String userId = memberService.createMember(createDto);
        return ResponseEntity.ok(userId);
    }

    //회원조회
    @GetMapping("/{userId}")
    public ResponseEntity<MemberDto.Response> getMember(@PathVariable String userId) {
        return ResponseEntity.ok(memberService.findMember(userId));
    }
}
