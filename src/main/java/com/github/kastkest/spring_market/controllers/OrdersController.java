package com.github.kastkest.spring_market.controllers;

import com.github.kastkest.spring_market.dto.Cart;
import com.github.kastkest.spring_market.dto.OrderDetailsDto;
import com.github.kastkest.spring_market.entities.User;
import com.github.kastkest.spring_market.exceptions.ResourceNotFoundException;
import com.github.kastkest.spring_market.services.CartService;
import com.github.kastkest.spring_market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final UserService userService;

    @PostMapping
    public void createOrder(Principal principal, @RequestBody OrderDetailsDto orderDetailsDto) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

}
