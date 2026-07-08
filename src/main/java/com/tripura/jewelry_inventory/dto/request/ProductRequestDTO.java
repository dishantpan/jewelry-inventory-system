package com.tripura.jewelry_inventory.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private String productName;

    private Long categoryId;

    private BigDecimal weightInGrams;

    private BigDecimal ratePerGram;
}