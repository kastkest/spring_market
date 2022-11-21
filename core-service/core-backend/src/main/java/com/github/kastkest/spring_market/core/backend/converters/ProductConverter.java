package com.github.kastkest.spring_market.core.backend.converters;


import com.github.kastkest.spring_market.core.backend.dto.ProductDto;
import com.github.kastkest.spring_market.core.backend.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }
}
