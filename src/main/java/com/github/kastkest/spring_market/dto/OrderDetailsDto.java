package com.github.kastkest.spring_market.dto;

import com.github.kastkest.spring_market.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    private String address;
    private String phone;

}
