package com.udacity.course3.reviews.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId; //maps SQL PRODUCT_ID to productId

    @Column(name = "NAME")
    private String productName; //maps SQL NAME to productName

    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<Review>(); //maps product to reviews

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public List<Review> getReviews() {
        return reviews;
    }
}
