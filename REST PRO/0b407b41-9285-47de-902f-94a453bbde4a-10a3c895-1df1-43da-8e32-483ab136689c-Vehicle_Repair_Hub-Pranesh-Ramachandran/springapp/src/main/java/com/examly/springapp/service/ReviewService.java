package com.examly.springapp.service;

import com.examly.springapp.entity.review;
import com.examly.springapp.entity.repairshop;
import com.examly.springapp.repository.reviewrepo;
import com.examly.springapp.repository.repairshoprepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private reviewrepo reviewRepository;

    @Autowired
    private repairshoprepo repairShopRepository;

    // Add a review for a repair shop
    public review addReview(Long repairShopId, review review) {
        Optional<repairshop> repairShop = repairShopRepository.findById(repairShopId);
        if (repairShop.isPresent()) {
            review.setRepairShop(repairShop.get());
            return reviewRepository.save(review);
        }
        return null;
    }

    // Get reviews for a repair shop
    public List<review> getReviewsByRepairShop(Long repairShopId) {
        return reviewRepository.findByRepairShopId(repairShopId);
    }

    // Get a review by ID
    public review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    // Get all reviews
    public List<review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Update an existing review
    public review updateReview(Long id, review updatedReview) {
        if (reviewRepository.existsById(id)) {
            updatedReview.setId(id);
            return reviewRepository.save(updatedReview);
        }
        return null;
    }

    // Delete a review
    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
