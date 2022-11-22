package com.github.kastkest.spring_market.integrations;


import com.github.kastkest.spring_market.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartsServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integrations.cart-service.url}")
    private String cartServiceUrl;


    public Optional<ProductDto> findById(Long id) {
        ProductDto productDto = restTemplate.getForObject(cartServiceUrl + "/api/v1/products/" + id, ProductDto.class);
        return Optional.ofNullable(productDto);
    }
}
