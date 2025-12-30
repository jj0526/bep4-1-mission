package com.back.global.jpa.entity;

import com.back.global.config.GlobalConfig;
import com.back.standard.modelType.CanGetModelTypeCode;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class BaseEntity implements CanGetModelTypeCode {
    public abstract long getId();
    public abstract LocalDateTime getCreateDate();
    public abstract LocalDateTime getModifyDate();

    @Override
    public String getModelTypeCode() {
        return this.getClass().getSimpleName();
    }

    protected void publishEvent(Object event) {
        GlobalConfig.getEventPublisher().publish(event);
    }
}
