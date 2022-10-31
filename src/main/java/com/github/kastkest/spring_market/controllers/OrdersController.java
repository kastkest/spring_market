package com.github.kastkest.spring_market.controllers;


import com.github.kastkest.spring_market.converters.OrderConverter;
import com.github.kastkest.spring_market.dto.OrderDetailsDto;
import com.github.kastkest.spring_market.dto.OrderDto;
import com.github.kastkest.spring_market.entities.User;
import com.github.kastkest.spring_market.exceptions.ResourceNotFoundException;
import com.github.kastkest.spring_market.services.OrderService;
import com.github.kastkest.spring_market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderDetailsDto orderDetailsDto) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createOrder(user, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findOrdersByUsername(principal.getName()).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
