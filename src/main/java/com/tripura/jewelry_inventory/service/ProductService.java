package com.tripura.jewelry_inventory.service;

import com.tripura.jewelry_inventory.entity.Category;
import com.tripura.jewelry_inventory.entity.Product;
import com.tripura.jewelry_inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    private BigDecimal calculatetotalPrice(BigDecimal weightInGrams,
                                           BigDecimal rateperGram,
                                           BigDecimal makingChargesPercentage) {
        BigDecimal makingCargeMultiplier = BigDecimal.ONE
                .add(makingChargesPercentage.divide(BigDecimal.valueOf(100)));
        return weightInGrams
                .multiply(rateperGram)
                .multiply(makingCargeMultiplier)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public Product createProduct(Product product) {
        Category category = categoryService.getCategoryById(
                product.getCategory().getCategoryId()
        );
        product.setCategory(category);
        product.setDeleted(false);
        return productRepository.save(product);
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(
                        "Product with id " + productId + " not found"
                ));
    }

    public void softDeleteProduct(Long productId) {
        Product existingProduct = getProductById(productId);
        existingProduct.setDeleted(true);
        productRepository.save(existingProduct);
    }
}

