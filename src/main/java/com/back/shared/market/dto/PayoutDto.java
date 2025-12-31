package com.back.shared.market.dto;

import com.back.standard.modelType.HasModelTypeCode;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PayoutDto(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        long payeeId,
        String payeeNickname,
        LocalDateTime payoutDate,
        long amount,
        boolean isPayeeSystem
) implements HasModelTypeCode {
    @Override
    public String getModelTypeCode() {
        return "Payout";
    }
}
