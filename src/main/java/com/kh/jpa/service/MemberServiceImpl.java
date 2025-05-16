package com.kh.jpa.service;

import com.kh.jpa.dto.MemberDto;
import com.kh.jpa.entity.Member;
import com.kh.jpa.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String createMember(MemberDto.Create createDto) {

        Member member = createDto.toEntity();
        memberRepository.save(member);
        return member.getUserId(); // 영속상태의 member
    }

    @Override
    public MemberDto.Response findMember(String userId) {

        return memberRepository.findOne(userId)
                .map(MemberDto.Response::toDto)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

}
