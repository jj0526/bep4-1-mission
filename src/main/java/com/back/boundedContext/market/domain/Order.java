package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
}
