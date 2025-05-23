package com.kh.jpa.repository;

import com.kh.jpa.entity.Board;
import com.kh.jpa.enums.CommonEnums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepositoryV2 extends JpaRepository<Board, Long> {
    Page<Board> findByStatus(Pageable pageable, CommonEnums.Status status);

}
