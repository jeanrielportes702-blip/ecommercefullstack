package com.ws101.galit.ecommercefullstack.service;

import com.ws101.galit.ecommercefullstack.model.Category;
import com.ws101.galit.ecommercefullstack.model.Product;
import com.ws101.galit.ecommercefullstack.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for {@link Category} business logic.
 *
 * <p>All database operations are delegated to {@link CategoryRepository}.
 * Business validations (e.g., duplicate name check) are handled here
 * before reaching the persistence layer.</p>
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository = null;

    /**
     * Retrieve all categories from the database.
     *
     * @return list of all categories
     */
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Find a category by its ID.
     *
     * @param id the category's primary key
     * @return the found category
     * @throws ResourceNotFoundException if no category exists with that ID
     */
    @Transactional(readOnly = true)
    public Product getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow();
    }

    /**
     * Create and persist a new category.
     * Validates that the category name is not already taken.
     *
     * @param category the category to create
     * @return the saved category with generated ID
     * @throws DataIntegrityViolationException if a category with the same name already exists
     */
    public Category createCategory(Category category) {
        if (categoryRepository.existsByName1(category.getName())) {
            throw new DataIntegrityViolationException(
                    "Category with name '" + category.getName() + "' already exists");
        }
        return categoryRepository.save(category);
    }

    /**
     * Update an existing category.
     *
     * @param id              the ID of the category to update
     * @param updatedCategory new category data
     * @return the updated and saved category
     */
    public Category updateCategory(Long id, Category updatedCategory) {
        Product existing = getCategoryById(id);
        if (updatedCategory.getName() != null)        existing.setName(updatedCategory.getName());
        if (updatedCategory.getDescription() != null) existing.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(existing);
    }

    /**
     * Delete a category by its ID.
     *
     * @param id the ID to delete
     * @throws ResourceNotFoundException if the category does not exist
     */
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsByName(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }
}
