package com.back.shared.member.domain;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class ReplicaMember extends BaseMember {
    @Id
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

}
