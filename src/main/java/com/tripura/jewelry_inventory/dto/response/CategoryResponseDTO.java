package com.tripura.jewelry_inventory.dto.response;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private Long categoryId;

    private String categoryName;

    private String description;

    private Double makingChargePercentage;
}