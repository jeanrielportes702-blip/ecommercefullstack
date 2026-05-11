package com.ws101.galit.ecommercefullstack.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Product belongs to one Category.
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;


    private String imageUrl;

    /**
     * Many Products belong to one Category.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Product category2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCategory'");
    }

    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getDescription() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescription'");
    }

    public void setDescription(Object description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDescription'");
    }

    public void setPrice(double price) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getPrice() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}