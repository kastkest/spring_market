package com.github.kastkest.spring_market.services;


import com.github.kastkest.spring_market.dto.ProductDto;
import com.github.kastkest.spring_market.entities.Product;
import com.github.kastkest.spring_market.exceptions.ResourceNotFoundException;
import com.github.kastkest.spring_market.repositories.ProductsRepository;
import com.github.kastkest.spring_market.repositories.specifications.ProductsSpecifications;
import com.github.kastkest.spring_market.soap.products.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }

        return productsRepository.findAll(spec, PageRequest.of(page - 1, 8));
    }

    public static final Function<Product, Products> functionEntityToSoap = pe -> {
        Products p = new Products();
        p.setId(pe.getId());
        p.setTitle(pe.getTitle());
        p.setPrice(pe.getPrice());
        return p;
    };

    public List<Products> getAllProducts() {
        return productsRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productsRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукта, не надйен в базе, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }
}