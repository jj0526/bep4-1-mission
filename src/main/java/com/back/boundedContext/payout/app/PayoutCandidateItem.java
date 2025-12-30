package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.PayoutEventType;
import com.back.boundedContext.payout.domain.PayoutItem;
import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAYOUT_PAYOUT_CANDIDATE_ITEM")
@NoArgsConstructor
@Getter
@SuperBuilder
public class PayoutCandidateItem extends BaseIdAndTime {

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

    @OneToOne(fetch = FetchType.LAZY)
    @Setter
    private PayoutItem payoutItem;

}
