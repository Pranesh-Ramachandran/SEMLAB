package com.examly.springapp.model;

import com.examly.springapp.entity.review;
import com.examly.springapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    
    @PostMapping("/{repairShopId}")
    public ResponseEntity<review> addReview(@PathVariable Long repairShopId, @RequestBody review review) {
        review newReview = reviewService.addReview(repairShopId, review);
        return newReview != null ? ResponseEntity.status(HttpStatus.CREATED).body(newReview) : ResponseEntity.badRequest().build();
    }

    
    @GetMapping("/repairShop/{repairShopId}")
    public ResponseEntity<List<review>> getReviewsByRepairShop(@PathVariable Long repairShopId) {
        List<review> reviews = reviewService.getReviewsByRepairShop(repairShopId);
        return ResponseEntity.ok(reviews);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<review> getReviewById(@PathVariable Long id) {
        review review = reviewService.getReviewById(id);
        return review != null ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
    }

    
    @GetMapping
    public ResponseEntity<List<review>> getAllReviews() {
        List<review> reviewList = reviewService.getAllReviews();
        return ResponseEntity.ok(reviewList);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<review> updateReview(@PathVariable Long id, @RequestBody review updatedReview) {
        review updated = reviewService.updateReview(id, updatedReview);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        boolean isDeleted = reviewService.deleteReview(id);
        return isDeleted ? ResponseEntity.ok("{\"message\": \"Review deleted successfully\"}") : ResponseEntity.notFound().build();
    }
}
