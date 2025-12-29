package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseManualIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MARKET_CART")
@NoArgsConstructor
@Getter
@SuperBuilder
public class Cart extends BaseManualIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember customer;

    @OneToMany(mappedBy = "cart", cascade =
            {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    private int itemsCount = 0;

    public static Cart from(MarketMember marketMember) {
        return Cart.builder()
                .id(marketMember.getId())
                .customer(marketMember)
                .build();
    }

    public boolean hasItems() {
        return itemsCount > 0;
    }

    public void addItem(Product product) {
        CartItem cartItem = CartItem.from(this, product);
        this.cartItems.add(cartItem);
        this.itemsCount++;
    }
}
