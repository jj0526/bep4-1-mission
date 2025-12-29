package com.back.shared.market.event;

import com.back.shared.market.dto.OrderDto;
import lombok.Builder;

@Builder
public record MarketOrderPaymentRequestedEvent(
        OrderDto orderDto,
        long pgPaymentAmount
) {
}
