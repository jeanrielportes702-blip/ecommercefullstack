package com.ws101.galit.ecommercefullstack.repository;

import java.util.List;
import java.util.Optional;

import com.ws101.galit.ecommercefullstack.model.Category;
import com.ws101.galit.ecommercefullstack.model.Product;

public class CategoryRepository {

    public Optional<Product> findById(Long categoryId) {
       
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public List<Category> findAll() {
      
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public boolean existsByName1(String name) {
        
        throw new UnsupportedOperationException("Unimplemented method 'existsByName'");
    }

    public boolean existsByName(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Category save(Category category) {
       
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public Category save(Product existing) {
        
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
