package com.back.boundedContext.cash.domain;

import com.back.global.jpa.entity.BaseEntity;
import com.back.global.jpa.entity.BaseManualIdAndTime;
import com.back.shared.cash.dto.WalletDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CASH_WALLET")
@NoArgsConstructor
@Getter
@SuperBuilder
public class Wallet extends BaseManualIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private CashMember holder;

    @Getter
    private long balance = 0;

    @OneToMany(mappedBy = "wallet", cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    List<CashLog> cashLogs = new ArrayList<>();

    public static Wallet from(CashMember holder) {
        return Wallet.builder()
                .holder(holder)
                .id(holder.getId())
                .build();
    }

    public boolean hasBalance() {
        return balance > 0;
    }

    public void credit(long amount, CashLog.EventType eventType, String relTypeCode, long relId) {
        this.balance += amount;

        addCashLog(amount, eventType, relTypeCode, relId);
    }


    public void credit(long amount, CashLog.EventType eventType, BaseEntity rel) {
        credit(amount, eventType, rel.getModelTypeCode(), rel.getId());
    }

    public void credit(long amount, CashLog.EventType eventType) {
        credit(amount, eventType, holder);
    }

    public void debit(long amount, CashLog.EventType eventType, String relTypeCode, long relId) {
        this.balance -= amount;

        addCashLog(-amount, eventType, relTypeCode, relId);
    }

    public void debit(long amount, CashLog.EventType eventType, BaseEntity rel) {
        debit(amount, eventType, rel.getModelTypeCode(), rel.getId());
    }

    public void debit(long amount, CashLog.EventType eventType) {
        debit(amount, eventType, holder);
    }

    private CashLog addCashLog(long amount, CashLog.EventType eventType, String relTypeCode, long relId) {
        CashLog cashLog = CashLog.builder()
                .wallet(this)
                .eventType(eventType)
                .relTypeCode(relTypeCode)
                .relId(relId)
                .amount(amount)
                .balance(this.balance)
                .build();

        this.cashLogs.add(cashLog);

        return cashLog;
    }

    public WalletDto toDto() {
        return WalletDto.builder()
                .id(getId())
                .createDate(getCreateDate())
                .modifyDate(getModifyDate())
                .holderId(getHolder().getId())
                .holderName(getHolder().getUsername())
                .balance(getBalance())
                .build();
    }
}
