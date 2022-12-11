package com.github.kastkest.spring_market.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель продукта")
public class ProductDto {
    @Schema(description = "Id продукта", required = true, example = "1")
    private Long id;
    @Schema(description = "Название продукта", required = true, maxLength = 255, minLength = 3, example = "Milk")
    private String title;
    @Schema(description = "Цена продукта", required = true, example = "200.00")
    private Integer price;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
