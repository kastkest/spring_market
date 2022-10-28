package com.github.kastkest.spring_market.services;

import com.github.kastkest.spring_market.dto.Cart;
import com.github.kastkest.spring_market.dto.OrderDetailsDto;
import com.github.kastkest.spring_market.dto.OrderItemDto;
import com.github.kastkest.spring_market.entities.Order;
import com.github.kastkest.spring_market.entities.OrderItem;
import com.github.kastkest.spring_market.entities.User;
import com.github.kastkest.spring_market.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CartService cartService;

    @Transactional
    public void createOrder(User user, OrderDetailsDto orderDetailsDto) {
        Cart currentCart = cartService.getCurrentCart();

        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUser(user);
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> items = currentCart.getItems()
                .stream().map(orderItemDto -> {OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setPricePerProduct(1);
                    return item;
                }).collect(Collectors.toList());
    }
}
