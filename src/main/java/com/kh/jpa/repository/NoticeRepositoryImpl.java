package com.kh.jpa.repository;

import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Notice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Notice notice) {
        em.persist(notice);
    }

    @Override
    public Optional<Notice> findOne(Long noticeNo) {
        return Optional.ofNullable(em.find(Notice.class, noticeNo));
    }

    @Override
    public void delete(Notice notice) {
        em.remove(notice);
    }

    @Override
    public List<Notice> searchNoticeByKeyword(String keyword) {
        String query = "select n from Notice n where n.noticeTitle like :title";
        return em.createQuery(query, Notice.class)
                .setParameter("title", "%" + keyword + "%")
                .getResultList();
    }

    @Override
    public List<Notice> findNoticeAll() {
        String query = "select n from Notice n";
        return em.createQuery(query, Notice.class).getResultList();
    }
}
