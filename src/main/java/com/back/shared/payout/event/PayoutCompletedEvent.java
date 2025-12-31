package com.back.shared.payout.event;

import com.back.shared.market.dto.PayoutDto;

public record PayoutCompletedEvent(
        PayoutDto payout
) {
}
