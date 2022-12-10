package com.github.kastkest.spring_market.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@Data

@ConfigurationProperties(prefix = "integrations.cart-service")
public class CartServiceIntegrationProperties {

    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
