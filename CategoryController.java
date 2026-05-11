package com.ws101.galit.ecommercefullstack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ws101.galit.ecommercefullstack.model.Category;
import com.ws101.galit.ecommercefullstack.model.Product;
import com.ws101.galit.ecommercefullstack.service.CategoryService;

import java.util.List;

/**
 * REST Controller for {@link Category} resources.
 * All routes are prefixed with {@code /api/categories}.
 */
@RestController
@RequestMapping("/api/categories")
public record CategoryController(CategoryService categoryService) {

    /** GET /api/categories — list all categories */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /** GET /api/categories/{id} — get one category */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    /** POST /api/categories — create a new category */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(category));
    }

    /** PUT /api/categories/{id} — update existing category */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @RequestBody Category category) {

        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    /** DELETE /api/categories/{id} — remove a category */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}