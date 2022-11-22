package com.github.kastkest.spring_market.api.core;


public class OrderItemDto {

    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(Long productId, String productTitle, int quantity, int pricePerProduct, int price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

}
