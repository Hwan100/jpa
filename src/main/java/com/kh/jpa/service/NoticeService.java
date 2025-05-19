package com.kh.jpa.service;

import com.kh.jpa.dto.NoticeDto;

import java.util.List;

public interface NoticeService {
    Long createNotice(NoticeDto.Create createDto, String userId);
    NoticeDto.Response findNotice(Long noticeNo);
    NoticeDto.Response updateNotice(NoticeDto.Update updateDto, Long noticeNo);
    void deleteNotice(Long noticeNo);
    List<NoticeDto.Response> searchNoticeByKeyword(String keyword);
}
