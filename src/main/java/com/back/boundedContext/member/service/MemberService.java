package com.back.boundedContext.member.service;


import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.enums.ActivityType;
import com.back.global.exception.DomainException;
import com.back.boundedContext.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long count() {
        return memberRepository.count();
    }

    public Member join(String username, String password, String nickname) {
        findByUsername(username).ifPresent(m -> {
            throw new DomainException("409-1", "이미 존재하는 username 입니다.");
        });

        return memberRepository.save(new Member(username, password, nickname));
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public void increaseScore(Member member, ActivityType activityType){
        member.increaseScore(activityType.getScore());
    }

    public Member findByMemberId(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new DomainException("MEMBER_NOT_FOUND", "회원이 존재하지 않습니다."));
    }
}
