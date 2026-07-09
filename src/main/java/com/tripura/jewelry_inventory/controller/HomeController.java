package com.tripura.jewelry_inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        Map<String, String> response = new HashMap<>();
        response.put("project", "Jewelry Inventory Management System");
        response.put("developer", "Dishant Panchal");
        response.put("version", "2.0");
        response.put("status", "Running");
        response.put("categories_api", "/api/categories");
        response.put("products_api", "/api/products");
        response.put("github", "https://github.com/dishantpan/jewelry-inventory-system");
        return ResponseEntity.ok(response);
    }
}