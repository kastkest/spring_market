package com.github.kastkest.spring_market.cart.exceptions;

public class CartBrokenException extends RuntimeException {
    public CartBrokenException(String message) {
        super(message);
    }
}
