package com.kh.jpa.repository;

import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository{
    void save(Notice notice);
    Optional<Notice> findOne(Long noticeNo);
    void delete(Notice notice);
    List<Notice> searchNoticeByKeyword(String keyword);
}
