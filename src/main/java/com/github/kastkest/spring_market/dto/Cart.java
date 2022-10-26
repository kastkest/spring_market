package com.github.kastkest.spring_market.dto;

import com.github.kastkest.spring_market.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void removeProduct(Long id) {
        items.removeIf(o -> o.getProductId().equals(id));
        recalculate();
    }

    public void decreaseProduct(Long id) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto item = iter.next();
            if (item.getProductId().equals(id)) {
                item.changeQty(-1);
                if (item.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void clear() {
        items.clear();
        totalPrice = 0;
    }

    private void recalculate() {
        totalPrice = 0;
        for (OrderItemDto item : items) {
            totalPrice += item.getPrice();
        }
    }

}
