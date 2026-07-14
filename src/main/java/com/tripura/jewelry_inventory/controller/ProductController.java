package com.tripura.jewelry_inventory.controller;

import com.tripura.jewelry_inventory.dto.request.ProductRequestDTO;
import com.tripura.jewelry_inventory.dto.response.ProductResponseDTO;
import com.tripura.jewelry_inventory.entity.Category;
import com.tripura.jewelry_inventory.entity.Product;
import com.tripura.jewelry_inventory.service.CategoryService;
import com.tripura.jewelry_inventory.service.ProductService;
import com.tripura.jewelry_inventory.strategy.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PricingStrategy pricingStrategy;

    private Product convertToEntity(ProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setProductName(requestDTO.getProductName());
        product.setWeightInGrams(requestDTO.getWeightInGrams());
        product.setRatePerGram(requestDTO.getRatePerGram());
        Category category = new Category();
        category.setCategoryId(requestDTO.getCategoryId());
        product.setCategory(category);
        return product;
    }

    private ProductResponseDTO convertToResponseDTO(Product product) {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setProductId(product.getProductId());
        responseDTO.setProductName(product.getProductName());
        responseDTO.setCategoryName(product.getCategory().getCategoryName());
        responseDTO.setWeightInGrams(product.getWeightInGrams());
        responseDTO.setRatePerGram(product.getRatePerGram());
        responseDTO.setTotalPrice(pricingStrategy.calculateTotalPrice(
                product.getWeightInGrams(),
                product.getRatePerGram(),
                BigDecimal.valueOf(product.getCategory().getMakingChargePercentage())
        ));
        return responseDTO;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
           @Valid @RequestBody ProductRequestDTO requestDTO) {
        Product savedProduct = productService.createProduct(convertToEntity(requestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponseDTO(savedProduct));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllActiveProducts()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(convertToResponseDTO(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.softDeleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO requestDTO) {
        Product updatedProduct = productService.updateProduct(id, requestDTO);
        return ResponseEntity.ok(convertToResponseDTO(updatedProduct));
    }
}