package com.back.shared.member.MemberJoinedEvent;

import com.back.shared.member.dto.MemberDto;

public record MemberModifiedEvent(
        MemberDto memberDto
) {
}
