package com.tripura.jewelry_inventory.repository;

import com.tripura.jewelry_inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends JpaRepository<Product, Long>{

}
