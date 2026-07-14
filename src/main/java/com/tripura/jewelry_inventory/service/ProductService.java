package com.tripura.jewelry_inventory.service;

import com.tripura.jewelry_inventory.entity.Category;
import com.tripura.jewelry_inventory.entity.Product;
import com.tripura.jewelry_inventory.exception.ResourceNotFoundException;
import com.tripura.jewelry_inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.tripura.jewelry_inventory.dto.request.ProductRequestDTO;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product createProduct(Product product) {
        Category category = categoryService.getCategoryById(
                product.getCategory().getCategoryId()
        );
        product.setCategory(category);
        product.setDeleted(false);
        return productRepository.save(product);
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.findAllByDeletedFalse();
    }

    public Product getProductById(Long productId) {
        return productRepository.findByProductIdAndDeletedFalse(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + productId + " not found"
                ));
    }

    public void softDeleteProduct(Long productId) {
        Product existingProduct = getProductById(productId);
        existingProduct.setDeleted(true);
        productRepository.save(existingProduct);
    }
    public Product updateProduct(Long productId, ProductRequestDTO requestDTO) {
        Product existingProduct = getProductById(productId);
        existingProduct.setProductName(requestDTO.getProductName());
        existingProduct.setWeightInGrams(requestDTO.getWeightInGrams());
        existingProduct.setRatePerGram(requestDTO.getRatePerGram());
        Category category = categoryService.getCategoryById(requestDTO.getCategoryId());
        existingProduct.setCategory(category);
        return productRepository.save(existingProduct);
    }
}

