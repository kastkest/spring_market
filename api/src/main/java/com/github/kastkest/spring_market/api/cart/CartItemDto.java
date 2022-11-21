package com.github.kastkest.spring_market.api.cart;

import com.github.kastkest.spring_market.api.core.ProductDto;

public class CartItemDto {
    private final Long productId;
    private final String productTitle;
    private final int quantity;
    private final int pricePerProduct;
    private final int price;

    public CartItemDto(Long productId, String productTitle, int quantity, int pricePerProduct, int price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }


}
