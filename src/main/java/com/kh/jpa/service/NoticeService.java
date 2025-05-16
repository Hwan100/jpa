package com.kh.jpa.service;

import com.kh.jpa.dto.NoticeDto;

public interface NoticeService {
    Long createNotice(NoticeDto.Create createDto, String userId);
    NoticeDto.Response findNotice(Long noticeNo);
    void deleteNotice(Long noticeNo);
}
