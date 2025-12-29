package com.back.shared.market.dto;

import com.back.boundedContext.market.domain.Order;

import java.time.LocalDateTime;

public record OrderDto(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        long customerId,
        String customerName,
        long price,
        long salePrice,
        LocalDateTime requestPaymentDate,
        LocalDateTime paymentDate
) {
    public OrderDto(Order order){
        this(
                order.getId(),
                order.getCreateDate(),
                order.getModifyDate(),
                order.getCustomer().getId(),
                order.getCustomer().getNickname(),
                order.getPrice(),
                order.getSalePrice(),
                order.getRequestPaymentDate(),
                order.getPaymentDate()
        );
    }
}
