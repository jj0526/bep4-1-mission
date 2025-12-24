package com.back.boundedContext.member.domain;

import com.back.shared.member.MemberJoinedEvent.MemberModifiedEvent;
import com.back.shared.member.domain.SourceMember;
import com.back.shared.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor
@Getter
public class Member extends SourceMember {

    public Member(String username, String password, String nickname) {
        super(username, password, nickname);
    }
    public void increaseScore(int plus){
        increaseScoreInternal(plus);

        publishEvent(new MemberModifiedEvent(MemberDto.from(this)));
    }
}
