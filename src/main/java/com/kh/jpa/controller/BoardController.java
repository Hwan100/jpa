package com.kh.jpa.controller;

import com.kh.jpa.dto.BoardDto;
import com.kh.jpa.dto.PageResponse;
import com.kh.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    public final BoardService boardService;

    //게시글 목록 조회
    /*
    page 보고자하는 페이지 번호
    size 몇개씩 가지고 올것인지
    sort 정렬 기준 : 속성, 방향 (boardTitle, desc)
    * */

    @GetMapping
    public ResponseEntity<PageResponse<BoardDto.Response>> getBoards(
            @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(boardService.getBoardList(pageable)));
    }

    //게시글 상세 조회
    @GetMapping("/{boardNo}")
    public ResponseEntity<BoardDto.Response> getBoard(@PathVariable Long boardNo) {
        return ResponseEntity.ok(boardService.getBoardDetail(boardNo));
    }

    //게시글 작성
    @PostMapping
    public ResponseEntity<Long> createBoard(@ModelAttribute BoardDto.Create boardCreate) throws IOException {

        return ResponseEntity.ok(boardService.createBoard(boardCreate));

    }
    //게시글 수정
    @PatchMapping("/{boardNo}")
    public ResponseEntity<BoardDto.Response> updateBoard(
            @PathVariable Long boardNo,
            @ModelAttribute BoardDto.Update updateBoard
            ) throws IOException {
        return ResponseEntity.ok(boardService.updateBoard(boardNo, updateBoard));
    }

    //게시글 삭제
    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardNo) {
        boardService.deleteBoard(boardNo);
        return ResponseEntity.ok().build();
    }
    //게시글 조회수 증가
}
