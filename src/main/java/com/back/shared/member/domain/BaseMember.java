package com.back.shared.member.domain;

import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class BaseMember extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private int score = 0;

    public BaseMember(String username, String password, String nickname, int score) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.score = score;
    }

    protected void increaseScoreInternal(int plus) {
        this.score += plus;
    }

}
