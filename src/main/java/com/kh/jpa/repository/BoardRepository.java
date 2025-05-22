package com.kh.jpa.repository;

import com.kh.jpa.entity.Board;
import com.kh.jpa.enums.CommonEnums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardRepository {
    Page<Board> findByStatus(Pageable pageable, CommonEnums.Status status);
    Optional<Board> findById(Long boardNo);
    Long save(Board board);
    void delete(Board board);
}
