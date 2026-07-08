package com.tripura.jewelry_inventory.repository;

import com.tripura.jewelry_inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByDeletedFalse();

    Optional<Category> findByCategoryIdAndDeletedFalse(Long categoryId);
}
