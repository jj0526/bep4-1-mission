package com.back.global.jpa.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    public String getModelTypeCode() {
        return this.getClass().getSimpleName();
    }

    public abstract long getId();
    public abstract LocalDateTime getCreateDate();
    public abstract LocalDateTime getModifyDate();
}
