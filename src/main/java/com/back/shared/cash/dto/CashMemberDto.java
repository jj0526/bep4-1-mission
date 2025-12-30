package com.back.shared.cash.dto;

import com.back.boundedContext.cash.domain.CashMember;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CashMemberDto(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String username,
        String nickname,
        int score
) {
    public CashMemberDto(CashMember member) {
        this(
                member.getId(),
                member.getCreateDate(),
                member.getModifyDate(),
                member.getUsername(),
                member.getNickname(),
                member.getScore()
        );
    }
}
