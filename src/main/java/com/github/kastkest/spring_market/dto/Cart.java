package com.github.kastkest.spring_market.dto;

import com.github.kastkest.spring_market.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Cart {
    private List<OrderItemDto> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (addProduct(product.getId())) {
            return;
        }
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public boolean addProduct(Long id) {
        for (OrderItemDto item : items) {
            if (item.getProductId().equals(id)) {
                item.changeQty(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    private void recalculate() {
        totalPrice = 0;
        for (OrderItemDto item : items) {
            totalPrice += item.getPrice();
        }
    }

}
