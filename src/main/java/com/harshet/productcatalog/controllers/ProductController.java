package com.harshet.productcatalog.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshet.productcatalog.dtos.GenericProductDTO;
import com.harshet.productcatalog.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Field Injection
    // @Autowired
    private ProductService productService;

    // Constructor Injection
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    // Setter Injection
    /*
     * *@Autowired
     * public void setProductService(ProductService productService){
     * this.productService = productService;
     * }
     */

    @GetMapping
    public String getAllProducts() {
        return "Products";
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) {
        return this.productService.getProductById(id);
        // return "Id is " + id;
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product) {
        return this.productService.createProduct(product);
        // return "Created Product " + UUID.randomUUID();
    }

    @PutMapping("{id}")
    public void updateProductById() {
    }

    @DeleteMapping("{id}")
    public void deleteProductById() {
    }
}
