package com.back.shared.market.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderItemDto(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        long orderId,
        long buyerId,
        String buyerName,
        long sellerId,
        String sellerName,
        long productId,
        String productName,
        long price,
        long salePrice,
        double payoutRate
) {
}
