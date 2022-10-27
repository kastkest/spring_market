package com.github.kastkest.spring_market.services;

import com.github.kastkest.spring_market.dto.Cart;
import com.github.kastkest.spring_market.entities.Product;
import com.github.kastkest.spring_market.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductsService productsService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductByIdToCart(Long productId) {
        if (!getCurrentCart().addProduct(productId)) {
            Product product  = productsService.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Невозможно добавить в корзину. Продукт не найден, id" + productId));
            getCurrentCart().addProduct(productId);
        }
    }
}
