package com.back.shared.member.domain;

import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@NoArgsConstructor
@SuperBuilder
public abstract class BaseMember extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    @Builder.Default
    private int score = 0;

    protected void increaseScoreInternal(int plus) {
        this.score += plus;
    }

}
