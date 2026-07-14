package com.tripura.jewelry_inventory.controller;

import com.tripura.jewelry_inventory.dto.request.CategoryRequestDTO;
import com.tripura.jewelry_inventory.dto.response.CategoryResponseDTO;
import com.tripura.jewelry_inventory.entity.Category;
import com.tripura.jewelry_inventory.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private Category convertToEntity(CategoryRequestDTO requestDTO) {
        Category category = new Category();
        category.setCategoryName(requestDTO.getCategoryName());
        category.setDescription(requestDTO.getDescription());
        category.setMakingChargePercentage(requestDTO.getMakingChargePercentage());
        return category;
    }

    private CategoryResponseDTO convertToResponseDTO(Category category) {
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        responseDTO.setCategoryId(category.getCategoryId());
        responseDTO.setCategoryName(category.getCategoryName());
        responseDTO.setDescription(category.getDescription());
        responseDTO.setMakingChargePercentage(category.getMakingChargePercentage());
        return responseDTO;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(
            @Valid @RequestBody CategoryRequestDTO requestDTO) {
        Category savedCategory = categoryService.createCategory(convertToEntity(requestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponseDTO(savedCategory));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllActiveCategories()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(convertToResponseDTO(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.softDeleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO requestDTO) {
        Category updatedCategory = categoryService.updateCategory(id, requestDTO);
        return ResponseEntity.ok(convertToResponseDTO(updatedCategory));
    }
}