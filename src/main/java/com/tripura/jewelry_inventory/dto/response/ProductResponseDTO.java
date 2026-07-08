package com.tripura.jewelry_inventory.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long productId;

    private String productName;

    private String categoryName;

    private BigDecimal weightInGrams;

    private BigDecimal ratePerGram;

    private BigDecimal totalPrice;
}