package com.github.kastkest.spring_market.api.cart;

import java.util.List;

public class CartDto {

    private final List<CartItemDto> items;
    private final int totalPrice;

    public CartDto(List<CartItemDto> items, int totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
}
