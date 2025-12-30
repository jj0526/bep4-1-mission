package com.back.boundedContext.payout.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAYOUT_PAYOUT_ITEM")
@NoArgsConstructor
@SuperBuilder
public class PayoutItem extends BaseIdAndTime {

    @ManyToOne(fetch = FetchType.LAZY)
    private Payout payout;

    @Enumerated(EnumType.STRING)
    private PayoutEventType eventType;

    String relTypeCode;

    private long relId;

    private LocalDateTime paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payer;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payee;

    private long amount;


}
