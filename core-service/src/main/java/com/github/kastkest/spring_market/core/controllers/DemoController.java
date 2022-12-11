package com.github.kastkest.spring_market.core.controllers;

import com.github.kastkest.spring_market.api.dto.StringResponse;
import com.github.kastkest.spring_market.core.integrations.CartsServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private CartsServiceIntegration cartsServiceIntegration;

    @GetMapping
    public StringResponse demo() {
        cartsServiceIntegration.getUserCart("1");
        return new StringResponse("Ok");
    }
}
