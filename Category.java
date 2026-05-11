package com.ws101.galit.ecommercefullstack.model;

import jakarta.persistence.*;

/**
 * One Category can contain many Products.
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Category() {}

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * One-to-Many relationship:
     * One category has many products.
     */
}