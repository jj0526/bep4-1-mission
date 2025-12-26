package com.back.boundedContext.cash.domain;

import com.back.global.jpa.entity.BaseManualIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "CASH_WALLET")
@NoArgsConstructor
@Getter
@SuperBuilder
public class Wallet extends BaseManualIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private CashMember holder;

    public static Wallet from(CashMember holder){
        return Wallet.builder()
                .holder(holder)
                .id(holder.getId())
                .build();
    }
}
