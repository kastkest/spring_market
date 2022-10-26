package com.github.kastkest.spring_market.services;

import com.github.kastkest.spring_market.dto.Cart;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CartService {

    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }
}
