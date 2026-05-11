package com.ws101.galit.ecommercefullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ws101.galit.ecommercefullstack.model.Order;

import java.util.List;

/**
 * Repository interface for {@link Order} entities.
 *
 * <p>Extends {@link JpaRepository} to inherit CRUD operations.
 * Spring Data JPA generates all implementations at runtime.</p>
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finder method: retrieve all orders placed by a specific customer email.
     *
     * @param email the customer's email address
     * @return list of orders for that customer
     */
    List<Order> findByCustomerEmail(String email);

    /**
     * Finder method: retrieve all orders by status.
     *
     * @param status the order status to filter by
     * @return list of orders in that status
     */
    List<Order> findByStatus(Order.OrderStatus status);

    /**
     * Custom JPQL query: fetch orders with their items eagerly loaded
     * (avoids N+1 query problem for order detail views).
     *
     * @param orderId the ID of the order to fetch
     * @return the order with its items joined
     */
    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.id = :id")
    Order findByIdWithItems(@Param("id") Long orderId);
}
