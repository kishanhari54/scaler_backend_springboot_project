package com.harshet.productcatalog.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getAllProducts() {
        return "Products";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Id is " + id;
    }

    @PostMapping
    public String createProduct() {
        return "Created Product " + UUID.randomUUID();
    }

    @PutMapping("{id}")
    public void updateProductById() {
    }

    @DeleteMapping("{id}")
    public void deleteProductById() {
    }
}
