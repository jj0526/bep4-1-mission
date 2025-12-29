package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Product;
import com.back.boundedContext.market.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCreateProductUseCase {
    private final ProductRepository productRepository;

    public Product saveProduct(
            MarketMember seller,
            String sourceTypeCode,
            long sourceId,
            String name,
            String description,
            long price,
            long salePrice
    ) {
        Product product = Product.from(seller, sourceTypeCode, sourceId,
                name, description, price, salePrice);

        return productRepository.save(product);
    }

}
