package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.shared.market.dto.OrderDto;
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

    public void addItem(Product product) {
        OrderItem orderItem = OrderItem.from(
                this, product);

        orderItems.add(orderItem);

        price += product.getPrice();
        salePrice += product.getSalePrice();
    }

    public void completePayment(){
        this.paymentDate = LocalDateTime.now();
    }

    public boolean isPaid() {
        return paymentDate != null;
    }

    public void requestPayment(long pgPaymentAmount){
        requestPaymentDate = LocalDateTime.now();

        publishEvent(
                MarketOrderPaymentRequestedEvent.builder()
                        .orderDto(new OrderDto(this))
                        .pgPaymentAmount(pgPaymentAmount)
                        .build()
        );
    }

    public void cancelRequestPayment() {
        this.requestPaymentDate = null;
    }
}
