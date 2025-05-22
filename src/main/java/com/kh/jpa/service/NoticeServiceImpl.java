package com.kh.jpa.service;

import com.kh.jpa.dto.MemberDto;
import com.kh.jpa.dto.NoticeDto;
import com.kh.jpa.entity.Member;
import com.kh.jpa.entity.Notice;
import com.kh.jpa.repository.MemberRepository;
import com.kh.jpa.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long createNotice(NoticeDto.Create createDto, String userId) {
        Member member = memberRepository.findOne(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));

        Notice notice = createDto.toEntity(member);
        noticeRepository.save(notice);
        return notice.getNoticeNo();
    }

    @Transactional(readOnly = true)
    @Override
    public NoticeDto.Response findNotice(Long noticeNo) {

        return noticeRepository.findOne(noticeNo)
                .map(NoticeDto.Response::toDto)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));

    }

    @Override
    public NoticeDto.Response updateNotice(NoticeDto.Update updateDto, Long noticeNo) {
        Notice notice = noticeRepository.findOne(noticeNo)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));

        notice.updateNoticeInfo(
                updateDto.getNotice_title(),
                updateDto.getNotice_content()
        );

        return NoticeDto.Response.toDto(notice);
    }

    @Override
    public void deleteNotice(Long noticeNo) {
        Notice notice = noticeRepository.findOne(noticeNo)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));

        noticeRepository.delete(notice);
    }

    @Override
    public List<NoticeDto.Response> searchNoticeByKeyword(String keyword) {
        return noticeRepository.searchNoticeByKeyword(keyword).stream()
                .map(NoticeDto.Response::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<NoticeDto.Response> findNoticeAll() {
        return noticeRepository.findNoticeAll().stream()
                .map(NoticeDto.Response::toDto).toList();
    }

}
