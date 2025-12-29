package com.back.shared.market.dto;

import com.back.boundedContext.market.domain.MarketMember;

import java.time.LocalDateTime;

public record MarketMemberDto(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String username,
        String nickname,
        int activityScore
) {
    public MarketMemberDto(MarketMember marketMember) {
        this(
                marketMember.getId(),
                marketMember.getCreateDate(),
                marketMember.getModifyDate(),
                marketMember.getUsername(),
                marketMember.getNickname(),
                marketMember.getScore()
        );
    }
}
