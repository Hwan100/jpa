package com.kh.jpa.repository;

import com.kh.jpa.entity.Board;
import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Board> findByStatus(Pageable pageable, CommonEnums.Status status) {
        //게시글을 paging 처리해서 필요한만큼만 가져오기
        String query = "SELECT b FROM Board b WHERE b.status = :status";
        List<Board> boards = em.createQuery(query, Board.class)
                .setParameter("status", status)
                .setFirstResult((int) pageable.getOffset()) //어디서부터 가지고 올것인가
                .setMaxResults(pageable.getPageSize()) // 몇개를 가지고 올것인가
                .getResultList();

        String countQuery = "SELECT COUNT(b) FROM Board b WHERE b.status = :status";
        Long totalCount = em.createQuery(countQuery, Long.class)
                .setParameter("status", status)
                .getSingleResult();

        //page<T> 인터페이스의 기본구현체를 통해서 paging한 정보를 한번에 전달 할 수 있음.
        //new PageImpl<>(content, pageable, total);
        return new PageImpl<Board>(boards, pageable, totalCount);
    }

    @Override
    public Optional<Board> findById(Long boardNo) {
        if (boardNo == null) {return Optional.empty();}
        return Optional.ofNullable(em.find(Board.class, boardNo));
    }

    @Override
    public Long save(Board board) {
        em.persist(board);
        return board.getBoardNo();
    }

    @Override
    public void delete(Board board) {
        em.remove(board);
    }
}
