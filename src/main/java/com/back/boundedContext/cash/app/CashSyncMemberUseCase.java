package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CashSyncMemberUseCase {
    private final CashMemberRepository cashMemberRepository;

    public CashMember syncMember(MemberDto memberDto) {
        CashMember cashMember = CashMember.from(memberDto);
        return cashMemberRepository.save(cashMember);
    }
}
