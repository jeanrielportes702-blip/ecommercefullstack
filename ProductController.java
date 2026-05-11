package com.ws101.galit.ecommercefullstack.controller;

import com.ws101.galit.ecommercefullstack.model.Product;
import com.ws101.galit.ecommercefullstack.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST Controller exposing CRUD endpoints for Product resources.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public ProductController() {
        this.productService = null;
    }

    // GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String search) {

        List<Product> products;

        if (search != null && !search.isBlank()) {
            products = productService.searchProducts(search);

        } else if (category != null && !category.isBlank()) {
            products = productService.getProductsByCategory(category);

        } else if (minPrice != null && maxPrice != null) {

            // convert BigDecimal to double if service uses double
            products = productService.getProductsByPriceRange(
                    minPrice.doubleValue(),
                    maxPrice.doubleValue());

        } else {
            products = productService.getAllProducts();
        }

        return ResponseEntity.ok(products);
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // CREATE PRODUCT
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @Valid @RequestBody Product product,
            @RequestParam(required = false) Long categoryId) {

        Product saved = productService.createProduct(product, categoryId);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updatedProduct,
            @RequestParam(required = false) Long categoryId) {

        Product updated =
                productService.updateProduct(id, updatedProduct, categoryId);

        return ResponseEntity.ok(updated);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}