package com.back.boundedContext.member.domain;

import com.back.shared.member.MemberJoinedEvent.MemberModifiedEvent;
import com.back.shared.member.domain.SourceMember;
import com.back.shared.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class Member extends SourceMember {

    public static Member from(String username, String password, String nickname) {
        return Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }

    public void increaseScore(int plus){
        increaseScoreInternal(plus);

        publishEvent(new MemberModifiedEvent(MemberDto.from(this)));
    }

    public MemberDto toDto() {
        return MemberDto.builder()
                .memberId(getId())
                .username(getUsername())
                .nickname(getNickname())
                .score(getScore())
                .createDate(getCreateDate())
                .modifyDate(getModifyDate())
                .build();
    }
}
