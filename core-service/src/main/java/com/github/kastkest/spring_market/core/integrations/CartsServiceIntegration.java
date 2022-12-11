package com.github.kastkest.spring_market.core.integrations;


import com.github.kastkest.spring_market.api.cart.CartDto;
import com.github.kastkest.spring_market.api.exceptions.AppError;
import com.github.kastkest.spring_market.cart.exceptions.CartServiceAppError;
import com.github.kastkest.spring_market.core.exceptions.CartServiceIntegrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartsServiceIntegration {
    private final WebClient cartServiceWebClient;


    public void clearUserCart(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();

    }

    public CartDto getUserCart(String username) {
        CartDto cart = cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError(),
                        clientResponse -> clientResponse.bodyToMono(CartServiceAppError.class).map(
                                body -> {
                                    if (body.getCode() == CartServiceAppError.CartServiceErrors.CART_IS_MISSED.name()) {
                                        return new CartServiceIntegrationException("Выполнен неккоректный запрос к сервису корзин: корзина не найдена");
                                    }
                                    if (body.getCode() == CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name()) {
                                        return new CartServiceIntegrationException("Выполнен неккоректный запрос к сервису корзин: корзина сломана");
                                    }
                                    return new CartServiceIntegrationException("Выполнен неккоректный запрос к сервису корзин: причина не известна");
                                }
                        )
                )
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CartServiceIntegrationException("Ошибка обращения к сервису корзин")))
//                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CartServiceIntegrationException("Сервис корзин сломался")))
                .bodyToMono(CartDto.class)
                .block();
        return cart;
    }
}
