package com.back.boundedContext.market.domain;

import com.back.shared.market.dto.MarketMemberDto;
import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "MARKET_MEMBER")
@Getter
@NoArgsConstructor
@SuperBuilder
public class MarketMember extends ReplicaMember {

    public static MarketMember from(MemberDto memberDto) {
        return MarketMember.builder()
                .id(memberDto.memberId())
                .createDate(memberDto.createDate())
                .modifyDate(memberDto.modifyDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .score(memberDto.score())
                .build();
    }

    public MarketMemberDto toDto() {
        return MarketMemberDto.builder()
                .id(getId())
                .createDate(getCreateDate())
                .modifyDate(getModifyDate())
                .username(getUsername())
                .nickname(getNickname())
                .activityScore(getScore())
                .build();
    }
}
