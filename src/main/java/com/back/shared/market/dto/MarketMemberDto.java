package com.back.shared.market.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MarketMemberDto(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String username,
        String nickname,
        int activityScore
) {
}
