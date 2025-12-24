package com.back.boundedContext.post.domain;

import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "POST_MEMBER")
@NoArgsConstructor
@Getter
public class PostMember extends ReplicaMember {
    public PostMember(String username, String password, String nickname) {
        super(username, password, nickname);
    }

    private PostMember(
            long id,
            String username,
            String nickname,
            LocalDateTime createDate,
            LocalDateTime modifyDate
    ) {
        super(id, username, nickname, createDate, modifyDate);
    }

    public static PostMember sync(MemberDto dto) {
        return new PostMember(
                dto.memberId(),
                dto.username(),
                dto.nickname(),
                dto.createDate(),
                dto.modifyDate()
        );
    }
}
