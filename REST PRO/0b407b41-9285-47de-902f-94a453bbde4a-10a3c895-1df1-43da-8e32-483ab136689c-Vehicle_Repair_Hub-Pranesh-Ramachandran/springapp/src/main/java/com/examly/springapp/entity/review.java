package com.examly.springapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewerName;
    private String comments;
    private int rating;

    @ManyToOne
@JoinColumn(name = "repair_shop_id", nullable = false)
    @JsonBackReference
    private repairshop repairShop;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public repairshop getRepairShop() {
        return repairShop;
    }

    public void setRepairShop(repairshop repairShop) {
        this.repairShop = repairShop;
    }
}
