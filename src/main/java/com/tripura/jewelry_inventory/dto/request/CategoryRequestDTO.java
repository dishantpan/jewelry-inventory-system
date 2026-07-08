package com.tripura.jewelry_inventory.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

    @NotBlank(message = "Category name cannot be empty")
    private String categoryName;

    private String description;

    @NotNull(message = "Making charge percentage cannot be null")
    @DecimalMin(value = "0.0", message = "Making charge percentage cannot be negative")
    private Double makingChargePercentage;
}