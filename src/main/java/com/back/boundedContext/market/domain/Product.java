package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "MARKET_PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class Product extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember seller;

    private String sourceTypeCode;

    private long sourceId;

    private String name;

    private String description;

    private int price;

    private int salePrice;

    public static Product from(MarketMember seller, String sourceTypeCode, long sourceId,
                               String name, String description, int price, int salePrice) {
        return Product.builder()
                .seller(seller)
                .sourceTypeCode(sourceTypeCode)
                .sourceId(sourceId)
                .name(name)
                .description(description)
                .price(price)
                .salePrice(salePrice)
                .build();
    }
}
