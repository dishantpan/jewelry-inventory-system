package com.tripura.jewelry_inventory.service;

import com.tripura.jewelry_inventory.entity.Category;
import com.tripura.jewelry_inventory.exception.ResourceNotFoundException;
import com.tripura.jewelry_inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tripura.jewelry_inventory.exception.ResourceNotFoundException;

import java.util.List;
import com.tripura.jewelry_inventory.dto.request.CategoryRequestDTO;

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
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + categoryId + " not found"));
    }

    public void softDeleteCategory(Long categoryId) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setDeleted(true);
        categoryRepository.save(existingCategory);
    }
    // existing category update karna — sirf active categories update ho sakti hain
    public Category updateCategory(Long categoryId, CategoryRequestDTO requestDTO) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setCategoryName(requestDTO.getCategoryName());
        existingCategory.setDescription(requestDTO.getDescription());
        existingCategory.setMakingChargePercentage(requestDTO.getMakingChargePercentage());
        return categoryRepository.save(existingCategory);
    }

}
