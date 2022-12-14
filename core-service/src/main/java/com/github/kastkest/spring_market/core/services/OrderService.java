package com.github.kastkest.spring_market.core.services;


import com.github.kastkest.spring_market.api.cart.CartDto;
import com.github.kastkest.spring_market.api.exceptions.ResourceNotFoundException;

import com.github.kastkest.spring_market.api.core.OrderDetailsDto;
import com.github.kastkest.spring_market.core.entities.Order;
import com.github.kastkest.spring_market.core.entities.OrderItem;
import com.github.kastkest.spring_market.core.repositories.OrdersRepository;
import com.github.kastkest.spring_market.core.integrations.CartsServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CartsServiceIntegration cartsServiceIntegration;
    private final ProductsService productsService;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) {
        CartDto currentCart = cartsServiceIntegration.getUserCart(username);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setPrice(o.getPrice());
                    item.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return item;
                }).collect(Collectors.toList());
        order.setItems(items);
        ordersRepository.save(order);
        cartsServiceIntegration.clearUserCart(username);
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }
}
