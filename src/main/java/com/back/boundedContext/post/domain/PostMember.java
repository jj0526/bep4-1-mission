package com.back.boundedContext.post.domain;

import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "POST_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class PostMember extends ReplicaMember {

    public static PostMember from(MemberDto dto) {
        return PostMember.builder()
                .id(dto.memberId())
                .username(dto.username())
                .nickname(dto.nickname())
                .password("")
                .score(dto.score())
                .createDate(dto.createDate())
                .modifyDate(dto.modifyDate())
                .build();
    }
}
