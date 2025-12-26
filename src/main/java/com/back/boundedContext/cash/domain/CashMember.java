package com.back.boundedContext.cash.domain;

import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "CASH_MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashMember extends ReplicaMember {

    public static CashMember from(MemberDto memberDto) {
        return CashMember.builder()
                .id(memberDto.memberId())
                .createDate(memberDto.createDate())
                .modifyDate(memberDto.modifyDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .score(memberDto.score())
                .build();
    }
}
