package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.shared.cash.dto.CashMemberDto;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashFacade {

    private final CashCreateWalletUseCase cashCreateWalletUseCase;
    private final CashSupport cashSupport;
    private final CashSyncMemberUseCase cashSyncMemberUseCase;
    private final CashCompleteOrderPaymentUseCase cashCompleteOrderPaymentUseCase;

    @Transactional
    public CashMember syncMember(MemberDto memberDto) {
        return cashSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional
    public Wallet createWallet(CashMemberDto cashMemberDto) {
        return cashCreateWalletUseCase.createWallet(cashMemberDto);
    }

    @Transactional(readOnly = true)
    public Optional<CashMember> findMemberByUsername(String username) {
        return cashSupport.findMemberByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Wallet> findWalletByHolder(CashMember holder) {
        return cashSupport.findWalletByHolder(holder);
    }

    public void completeOrderPayment(OrderDto order, long pgPaymentAmount) {
        cashCompleteOrderPaymentUseCase.completeOrderPayment(order,
                pgPaymentAmount);
    }

    @Transactional(readOnly = true)
    public Optional<Wallet> findWalletByHolderId(long holderId) {
        return cashSupport.findWalletByHolderId(holderId);
    }
}
