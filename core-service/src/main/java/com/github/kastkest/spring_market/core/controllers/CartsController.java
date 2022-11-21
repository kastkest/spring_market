package com.github.kastkest.spring_market.core.controllers;


import com.github.kastkest.spring_market.api.dto.StringResponse;
import com.github.kastkest.spring_market.core.dto.Cart;
import com.github.kastkest.spring_market.core.services.CartService;
import com.github.kastkest.spring_market.core.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor

public class CartsController {
    private final CartService cartService;
    private final ProductsService productsService;

    @GetMapping("/{uuid}")
    public Cart getCart(@RequestHeader(required = false) String username, @PathVariable String uuid) {

        return cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {

        cartService.addToCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {

        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {

        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clear(@RequestHeader(required = false) String username, @PathVariable String uuid) {

        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void merge(@RequestHeader String username, @PathVariable String uuid) {

        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}
