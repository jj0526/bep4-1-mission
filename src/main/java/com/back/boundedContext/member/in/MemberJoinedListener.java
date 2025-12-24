package com.back.boundedContext.member.in;

import com.back.shared.member.MemberJoinedEvent.MemberJoinedEvent;
import com.back.boundedContext.post.app.PostFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class MemberJoinedListener {

    private final PostFacade postFacade;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(MemberJoinedEvent memberJoinedEvent){
        postFacade.syncMember(memberJoinedEvent.memberDto());
    }
}
