package com.tripura.jewelry_inventory.service;

import com.tripura.jewelry_inventory.entity.Category;
import com.tripura.jewelry_inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllActiveCategories(){
        return categoryRepository.findAllByDeletedFalse();
    }

    public Category createCategory(Category category) {
        category.setDeleted(false);
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findByCategoryIdAndDeletedFalse(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with id " + categoryId + " not found"));
    }

    public void softDeleteCategory(Long categoryId) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setDeleted(true);
        categoryRepository.save(existingCategory);
    }

}
