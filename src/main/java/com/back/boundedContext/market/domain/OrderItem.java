package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.shared.market.dto.OrderItemDto;
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

    public OrderItemDto toDto() {
        return OrderItemDto.builder()
                .id(getId())
                .createDate(getCreateDate())
                .modifyDate(getModifyDate())
                .orderId(order.getId())
                .buyerId(order.getCustomer().getId())
                .buyerName(order.getCustomer().getNickname())
                .sellerId(product.getSeller().getId())
                .sellerName(product.getSeller().getNickname())
                .productId(product.getId())
                .productName(productName)
                .price(price)
                .salePrice(salePrice)
                .payoutRate(payoutRate)
                .payoutFee(getPayoutFee())
                .salePriceWithoutFee(getSalePriceWithoutFee())
                .build();
    }

    public long getPayoutFee() {
        return MarketPolicy.calculatePayoutFee(getSalePrice(), getPayoutRate());
    }

    public long getSalePriceWithoutFee() {
        return MarketPolicy.calculateSalePriceWithoutFee(getSalePrice(), getPayoutRate());
    }
}
