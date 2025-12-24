package com.back.shared.member.dto;

import com.back.boundedContext.member.domain.Member;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberDto(
        long memberId,
        String username,
        String nickname,
        LocalDateTime createDate,
        LocalDateTime modifyDate
) {
    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getId(),
                member.getUsername(),
                member.getNickname(),
                member.getCreateDate(),
                member.getModifyDate()
        );
    }
}
