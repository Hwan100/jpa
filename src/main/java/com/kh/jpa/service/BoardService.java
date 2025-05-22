package com.kh.jpa.service;

import com.kh.jpa.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;


public interface BoardService {
    Page<BoardDto.Response> getBoardList(Pageable pageable);
    BoardDto.Response getBoardDetail(Long BoardNo);
    Long createBoard(BoardDto.Create createBoard) throws IOException;
    void deleteBoard(Long BoardNo);
    BoardDto.Response updateBoard(Long boardNo, BoardDto.Update updateBoard) throws IOException;

}
