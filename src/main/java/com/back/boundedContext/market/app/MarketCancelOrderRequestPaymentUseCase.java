package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Order;
import com.back.boundedContext.market.out.OrderRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCancelOrderRequestPaymentUseCase {
    private final OrderRepository orderRepository;

    public void cancelRequestPayment(long orderId) {
        Order order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new DomainException("ORDER_NOT_FOUND", "주문을 찾을 수 없습니다"));

        order.cancelRequestPayment();
    }
}
