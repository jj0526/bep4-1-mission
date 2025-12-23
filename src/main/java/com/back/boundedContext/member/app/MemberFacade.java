package com.back.boundedContext.member.app;


import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.enums.ActivityType;
import com.back.global.RsData.RsData;
import com.back.global.exception.DomainException;
import com.back.boundedContext.member.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberFacade {
    private final MemberRepository memberRepository;
    private final MemberJoinUseCase memberJoinUseCase;

    @Transactional(readOnly = true)
    public long count() {
        return memberRepository.count();
    }

    public RsData<Member> join(String username, String password, String nickname) {
        return memberJoinUseCase.join(username, password, nickname);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Member findByMemberId(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new DomainException("MEMBER_NOT_FOUND", "회원이 존재하지 않습니다."));
    }
}
