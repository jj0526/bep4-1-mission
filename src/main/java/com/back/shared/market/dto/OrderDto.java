package com.back.shared.market.dto;

import com.back.boundedContext.market.domain.Order;
import com.back.standard.modelType.CanGetModelTypeCode;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
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
) implements CanGetModelTypeCode {
    @Override
    public String getModelTypeCode() {
        return "Order";
    }
}
