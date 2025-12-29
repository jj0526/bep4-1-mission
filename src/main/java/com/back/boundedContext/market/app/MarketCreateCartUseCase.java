package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.out.CartRepository;
import com.back.boundedContext.market.out.MarketMemberRepository;
import com.back.global.RsData.RsData;
import com.back.shared.market.dto.MarketMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCreateCartUseCase {
    private final CartRepository cartRepository;
    private final MarketMemberRepository marketMemberRepository;

    public RsData<Cart> createCart(MarketMemberDto marketMemberDto){
        MarketMember _customer = marketMemberRepository.getReferenceById(marketMemberDto.id());
        Cart cart = Cart.from(_customer);
        cart = cartRepository.save(cart);

        return new RsData<>(
                "201-1",
                "장바구니가 생성되었습니다.",
                cart
        );
    }
}
