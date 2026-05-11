package com.ws101.galit.ecommercefullstack.repository;

import com.ws101.galit.ecommercefullstack.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for {@link Product} entities.
 *
 * <p>Extends {@link JpaRepository} to inherit standard CRUD operations
 * (save, findById, findAll, deleteById, etc.) without any implementation code.
 * Spring Data JPA generates the implementation at runtime.</p>
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finder method using Spring Data JPA method naming convention.
     * Finds all products that belong to a category with the given name.
     *
     * <p>Spring Data JPA automatically translates the method name
     * {@code findByCategory_Name} into a JOIN query on the category relationship.</p>
     *
     * @param name the category name to filter by
     * @return list of products in the specified category
     */
    List<Product> findByCategory_Name(String name);

    /**
     * Custom JPQL query to find products within a specified price range.
     *
     * <p>JPQL (Java Persistence Query Language) operates on entity class names
     * and field names, not table/column names. The {@code :min} and {@code :max}
     * are named parameters bound via {@link Param}.</p>
     *
     * @param min minimum price (inclusive)
     * @param max maximum price (inclusive)
     * @return list of products whose price falls within [min, max]
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List<Product> findByPriceBetween(@Param("min") BigDecimal min,
                                     @Param("max") BigDecimal max);

    /**
     * Finder method: search products whose name contains a keyword (case-insensitive).
     *
     * @param keyword partial name to search
     * @return list of matching products
     */
    List<Product> findByNameContainingIgnoreCase(String keyword);

    /**
     * Custom JPQL query: find all products with stock below a given threshold.
     * Useful for inventory management / low-stock alerts.
     *
     * @param threshold stock level to compare against
     * @return products with stock less than the threshold
     */
    @Query("SELECT p FROM Product p WHERE p.stock < :threshold ORDER BY p.stock ASC")
    List<Product> findLowStockProducts(@Param("threshold") int threshold);

    /**
     * Finder method: find all products belonging to a specific category ID.
     *
     * @param categoryId the ID of the category
     * @return list of products in that category
     */
    List<Product> findByCategory_Id(Long categoryId);

    List<Product> findProductsByPriceRange(double min, double max);
}
