package com.back.shared.member.domain;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class ReplicaMember extends BaseMember {
    @Id
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public ReplicaMember(String username, String password, String nickname) {
        super(username, password, nickname);
    }

    protected ReplicaMember(
            long id,
            String username,
            String nickname,
            String password,
            LocalDateTime createDate,
            LocalDateTime modifyDate
    ) {
        super(username, password, nickname);
        this.id = id;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}