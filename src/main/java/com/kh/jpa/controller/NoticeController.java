package com.kh.jpa.controller;

import com.kh.jpa.dto.MemberDto;
import com.kh.jpa.dto.NoticeDto;
import com.kh.jpa.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    //공지등록
    @PostMapping("/{userId}")
    public ResponseEntity<Long> addNotice(@RequestBody NoticeDto.Create createDto, @PathVariable String userId) {
        Long noticeId = noticeService.createNotice(createDto, userId);
        return ResponseEntity.ok(noticeId);
    }

    //공지전체조회
    @GetMapping
    public ResponseEntity<List<NoticeDto.Response>> getNotices() {
        return ResponseEntity.ok(noticeService.findNoticeAll());
    }

    //특정공지조회
    @GetMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto.Response> getNotice(@PathVariable Long noticeNo) {
        return ResponseEntity.ok(noticeService.findNotice(noticeNo));
    }

    //공지수정
    @PutMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto.Response> updateNotice(@PathVariable Long noticeNo, @RequestBody NoticeDto.Update updateDto) {
        return ResponseEntity.ok(noticeService.updateNotice(updateDto, noticeNo));
    }

    //공지삭제
    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeNo) {
        noticeService.deleteNotice(noticeNo);
        return ResponseEntity.ok().build();
    }

    //타이틀 키워드로 공지조회
    @GetMapping("/search/keyword")
    public ResponseEntity<List<NoticeDto.Response>> searchNoticeByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(noticeService.searchNoticeByKeyword(keyword));
    }
}
