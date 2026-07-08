package com.tripura.jewelry_inventory.repository;

import com.tripura.jewelry_inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
