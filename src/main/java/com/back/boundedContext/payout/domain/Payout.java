package com.back.boundedContext.payout.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PAYOUT_PAYOUT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class Payout extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payee;

    @Setter
    private LocalDateTime payoutDate;

    private long amount = 0;

    @OneToMany(mappedBy = "payout", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<PayoutItem> items = new ArrayList<>();

    public static Payout from(PayoutMember payee) {
        return Payout.builder()
                .payee(payee)
                .build();
    }

    public PayoutItem addItem(PayoutEventType eventType, String relTypeCode, long relId,
                              LocalDateTime payDate, PayoutMember payer, PayoutMember payee, long amount) {
        PayoutItem payoutItem = PayoutItem.builder()
                .payout(this)
                .eventType(eventType)
                .relTypeCode(relTypeCode)
                .relId(relId)
                .paymentDate(payDate)
                .payer(payer)
                .payee(payee)
                .amount(amount)
                .build();

        items.add(payoutItem);

        this.amount += amount;

        return payoutItem;
    }
}
