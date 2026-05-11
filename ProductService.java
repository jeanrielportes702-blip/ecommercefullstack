package com.ws101.galit.ecommercefullstack.service;

import com.ws101.galit.ecommercefullstack.model.Product;
import com.ws101.galit.ecommercefullstack.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET PRODUCT BY ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
    }

    // CREATE PRODUCT
    public Product createProduct(Product product, Long categoryId) {

        // If you have Category relationship,
        // set the category here before saving

        return productRepository.save(product);
    }

    // SAVE PRODUCT
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // UPDATE PRODUCT
    public Product updateProduct(Long id, Product updatedProduct, Long categoryId) {

        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setQuantity(updatedProduct.getQuantity());
        existing.setImageUrl(updatedProduct.getImageUrl());

        return productRepository.save(existing);
    }

    // DELETE PRODUCT
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // FIND PRODUCTS BY CATEGORY
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategory_Name(categoryName);
    }

    // FIND PRODUCTS BY PRICE RANGE
    public List<Product> getProductsByPriceRange(double min, double max) {
        return productRepository.findProductsByPriceRange(min, max);
    }

    // SEARCH PRODUCTS
    public List<Product> searchProducts(String search) {
        return productRepository.findByNameContainingIgnoreCase(search);
    }
}