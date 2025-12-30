package com.back.shared.payout.dto;

import java.time.LocalDateTime;

public record PayoutMemberDto (
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String username,
        String nickname,
        int activityScore
) {
}
