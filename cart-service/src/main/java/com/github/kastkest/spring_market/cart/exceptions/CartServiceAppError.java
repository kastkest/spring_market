package com.github.kastkest.spring_market.cart.exceptions;

import com.github.kastkest.spring_market.api.exceptions.AppError;

public class CartServiceAppError extends AppError {
    public enum CartServiceErrors {
        CART_IS_BROKEN,
        CART_ID_GENERATOR_DISABLED,
        CART_IS_MISSED
    }

    public CartServiceAppError(String code, String message) {
        super(code, message);
    }
}
