package com.back.boundedContext.payout.app;


import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.PayoutMemberRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.event.PayoutMemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutSyncMemberUseCase {
    private final PayoutMemberRepository payoutMemberRepository;
    private final EventPublisher eventPublisher;

    public PayoutMember syncMember(MemberDto memberDto) {
        boolean isNew = !payoutMemberRepository.existsById(memberDto.memberId());

        PayoutMember _member = payoutMemberRepository.save(
               PayoutMember.from(memberDto)
        );

        if (isNew) {
            eventPublisher.publish(
                    new PayoutMemberCreatedEvent(
                            _member.toDto()
                    )
            );
        }

        return _member;
    }
}
