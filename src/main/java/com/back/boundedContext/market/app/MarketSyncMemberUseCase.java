package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shared.market.event.MarketMemberCreatedEvent;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketSyncMemberUseCase {
    private final MarketMemberRepository marketMemberRepository;
    private final EventPublisher eventPublisher;

    public MarketMember syncMember(MemberDto memberDto) {
        boolean isNew = !marketMemberRepository.existsById(memberDto.memberId());

        MarketMember marketMember = MarketMember.from(memberDto);
        MarketMember _member = marketMemberRepository.save(marketMember);

        if (isNew) {
            eventPublisher.publish(
                    new MarketMemberCreatedEvent(
                            _member.toDto()
                    )
            );
        }
        return _member;
    }
}
