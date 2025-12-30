package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.PayoutEventType;
import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.PayoutCandidateItemRepository;
import com.back.global.exception.DomainException;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.market.dto.OrderItemDto;
import com.back.shared.market.out.MarketApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayoutAddPayoutCandidateItemsUseCase {
    private final MarketApiClient marketApiClient;
    private final PayoutSupport payoutSupport;

    private final PayoutCandidateItemRepository payoutCandidateItemRepository;

    public void addPayoutCandidateItems(OrderDto orderDto) {
        marketApiClient.getOrderItems(orderDto.id())
                        .forEach(orderItemDto -> makePayoutCandidateItems(orderDto, orderItemDto));
    }

    private void makePayoutCandidateItems(OrderDto orderDto, OrderItemDto orderItemDto) {
        PayoutMember system = payoutSupport.findSystemMember()
                .orElseThrow(() -> new DomainException("PAYOUT_HOLDING_MEMBER_NOT_FOUND",
                        "정산 시스템 회원을 찾을 수 없습니다"));

        PayoutMember buyer = payoutSupport.findMemberById(orderDto.customerId())
                .orElseThrow(() -> new DomainException("PAYOUT_MEMBER_NOT_FOUND", "정산 회원을 찾을 수 없습니다"));

        PayoutMember seller = payoutSupport.findMemberById(orderItemDto.sellerId())
                .orElseThrow(() -> new DomainException("PAYOUT_MEMBER_NOT_FOUND", "정산 회원을 찾을 수 없습니다"));;

        makePayoutCandidateItem(
                PayoutEventType.정산__상품판매_수수료,
                orderItemDto.getModelTypeCode(),
                orderItemDto.id(),
                orderDto.paymentDate(),
                buyer,
                system,
                orderItemDto.payoutFee()
        );

        makePayoutCandidateItem(
                PayoutEventType.정산__상품판매_대금,
                orderItemDto.getModelTypeCode(),
                orderItemDto.id(),
                orderDto.paymentDate(),
                buyer,
                seller,
                orderItemDto.salePriceWithoutFee()
        );
    }

    private void makePayoutCandidateItem(
            PayoutEventType eventType,
            String relTypeCode,
            long relId,
            LocalDateTime paymentDate,
            PayoutMember payer,
            PayoutMember payee,
            long amount
    ) {
        PayoutCandidateItem payoutCandidateItem = PayoutCandidateItem.builder()
                .eventType(eventType)
                .relTypeCode(relTypeCode)
                .relId(relId)
                .paymentDate(paymentDate)
                .payer(payer)
                .payee(payee)
                .amount(amount)
                .build();
        payoutCandidateItemRepository.save(payoutCandidateItem);
    }
}
