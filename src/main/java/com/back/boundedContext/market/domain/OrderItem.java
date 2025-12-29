package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "MARKET_ORDER_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class OrderItem extends BaseIdAndTime {

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private String productName;

    private long price;

    private long salePrice;

    private double payoutRate = MarketPolicy.PRODUCT_PAYOUT_RATE;


    public static OrderItem from(Order order, Product product) {
        return OrderItem.builder()
                .order(order)
                .product(product)
                .productName(product.getName())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .build();
    }
}
