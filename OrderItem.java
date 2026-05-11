package com.ws101.galit.ecommercefullstack.model;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;

import jakarta.persistence.*;
import lombok.*;

/**
 * OrderItem belongs to one Order and references one Product.
 */
@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Producer product;

    public int getQuantity() {
        return quantity;
    }

    public Order getOrder() {
        return order;
    }

    public Producer getProduct() {
        return product;
    }

    public void setProduct(Producer product) {
        this.product = product;
    }
}