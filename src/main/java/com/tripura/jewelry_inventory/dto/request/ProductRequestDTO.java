package com.tripura.jewelry_inventory.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Product name cannot be empty")
    private String productName;

    @NotNull(message = "Category id cannot be null")
    private Long categoryId;

    @NotNull(message = "Weight cannot be null")
    @DecimalMin(value = "0.1", message = "Weight must be greater than 0")
    private BigDecimal weightInGrams;

    @NotNull(message = "Rate per gram cannot be null")
    @DecimalMin(value = "0.1", message = "Rate per gram must be greater than 0")
    private BigDecimal ratePerGram;
}