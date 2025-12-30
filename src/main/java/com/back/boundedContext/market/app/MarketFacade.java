package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Order;
import com.back.boundedContext.market.domain.Product;
import com.back.global.RsData.RsData;
import com.back.shared.market.dto.MarketMemberDto;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MarketFacade {
    private final MarketSupport marketSupport;

    private final MarketSyncMemberUseCase marketSyncMemberUseCase;
    private final MarketCreateProductUseCase marketCreateProductUseCase;
    private final MarketCreateCartUseCase marketCreateCartUseCase;
    private final MarketCreateOrderUseCase marketCreateOrderUseCase;
    private final MarketCompleteOrderPaymentUseCase marketCompleteOrderPaymentUseCase;
    private final MarketCancelOrderRequestPaymentUseCase marketCancelOrderRequestPaymentUseCase;

    @Transactional
    public MarketMember syncMember(MemberDto memberDto) {
        return marketSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional(readOnly = true)
    public long productsCount() {
        return marketSupport.countProducts();
    }

    @Transactional
    public Product createProduct(
            MarketMember seller,
            String sourceTypeCode,
            long sourceId,
            String name,
            String description,
            long price,
            long salePrice
    ) {
        return marketCreateProductUseCase.saveProduct(
                seller,
                sourceTypeCode,
                sourceId,
                name,
                description,
                price,
                salePrice
        );
    }

    @Transactional(readOnly = true)
    public Optional<MarketMember> findMemberByUsername(String username) {
        return marketSupport.findMemberByUsername(username);
    }

    @Transactional
    public RsData<Cart> createCart(MarketMemberDto marketMemberDto){
        return marketCreateCartUseCase.createCart(marketMemberDto);
    }

    @Transactional(readOnly = true)
    public Optional<Cart> findCartByCustomer(MarketMember buyer) {
        return marketSupport.findCartByCustomer(buyer);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findProductById(int id) {
        return marketSupport.findProductById(id);
    }

    @Transactional(readOnly = true)
    public long ordersCount() {
        return marketSupport.countOrders();
    }

    @Transactional
    public RsData<Order> createOrder(Cart cart) {
        return marketCreateOrderUseCase.createOrder(cart);
    }

    @Transactional(readOnly = true)
    public Optional<Order> findOrderById(long id) {
        return marketSupport.findOrderById(id);
    }

    @Transactional
    public void requestPayment(Order order, long pgPaymentAmount) {
        order.requestPayment(pgPaymentAmount);
    }

    @Transactional
    public void completePayment(long orderId) {
        marketCompleteOrderPaymentUseCase.completePayment(orderId);
    }

    @Transactional
    public void cancelOrderRequestPayment(long orderId) {
        marketCancelOrderRequestPaymentUseCase.cancelRequestPayment(orderId);
    }
}
