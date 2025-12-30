package com.back.boundedContext.payout.domain;

import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.dto.PayoutMemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "PAYOUT_MEMBER")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayoutMember extends ReplicaMember {
    public static PayoutMember from(MemberDto memberDto) {
        return PayoutMember.builder()
                .id(memberDto.memberId())
                .createDate(memberDto.createDate())
                .modifyDate(memberDto.modifyDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .score(memberDto.score())
                .build();
    }

    public PayoutMemberDto toDto() {
        return new PayoutMemberDto(
                getId(),
                getCreateDate(),
                getModifyDate(),
                getUsername(),
                getNickname(),
                getScore()
        );
    }
}
