package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.market.event.MarketOrderPaymentCompletedEvent;
import com.back.shared.market.event.MarketOrderPaymentRequestedEvent;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MARKET_ORDER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SuperBuilder
public class Order extends BaseIdAndTime {

    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember customer;

    private LocalDateTime requestPaymentDate;

    private LocalDateTime paymentDate;

    private LocalDateTime cancelDate;

    private long price;

    private long salePrice;

    @OneToMany(mappedBy = "order", cascade =
            {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(Cart cart){
        this.customer = cart.getCustomer();
        cart.getCartItems().forEach(
                cartItem -> addItem(cartItem.getProduct())
        );
    }

    public OrderDto toDto() {
        return OrderDto.builder()
                .id(getId())
                .createDate(getCreateDate())
                .modifyDate(getModifyDate())
                .customerId(customer.getId())
                .customerName(customer.getNickname())
                .price(price)
                .salePrice(salePrice)
                .requestPaymentDate(requestPaymentDate)
                .paymentDate(paymentDate)
                .build();
    }

    public void addItem(Product product) {
        OrderItem orderItem = OrderItem.from(
                this, product);

        orderItems.add(orderItem);

        price += product.getPrice();
        salePrice += product.getSalePrice();
    }

    public void completePayment(){
        this.paymentDate = LocalDateTime.now();
        publishEvent(
                new MarketOrderPaymentCompletedEvent(
                        toDto()
                )
        );
    }

    public boolean isPaid() {
        return paymentDate != null;
    }

    public void requestPayment(long pgPaymentAmount){
        requestPaymentDate = LocalDateTime.now();

        publishEvent(
                new MarketOrderPaymentRequestedEvent(
                        toDto(), pgPaymentAmount)
        );
    }

    public void cancelRequestPayment() {
        this.requestPaymentDate = null;
    }

    public boolean isCanceled() {
        return cancelDate != null;
    }

    public boolean isPaymentInProgress() {
        return requestPaymentDate != null && paymentDate == null && cancelDate == null;
    }
}
