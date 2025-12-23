package com.back.boundedContext.member.eventListener;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.member.dto.MemberScoreEvent;
import com.back.boundedContext.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class MemberEventListener {

    private final MemberService memberService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(MemberScoreEvent memberScoreEvent){
        Member member = memberService.findByMemberId(memberScoreEvent.memberId());
        member.increaseScore(memberScoreEvent.activityType().getScore());
    }
}
