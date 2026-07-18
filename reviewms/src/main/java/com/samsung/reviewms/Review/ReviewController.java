package com.samsung.reviewms.Review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    ReviewService reviewService;
    private ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviewsByCompanyId(
            @RequestParam Long companyId
    ){
        List<Review> reviews = reviewService.getReviewsByCompanyId(companyId);
        return ResponseEntity.
                status((HttpStatus.OK)).
                body(reviews);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,@RequestBody Review review){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.createReviewByCompanyId(companyId,review));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable("reviewId") Long reviewId
    ){
        Review review=reviewService.findReviewById(reviewId);
        return ResponseEntity.
                status(HttpStatus.OK).
                body(review);//this review can be null if company with companyId do not exists
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(
            @PathVariable Long reviewId,
            @RequestBody Review review
    ){
        boolean isReviewUpdated = reviewService.updateReviewByid(reviewId,review);
        if(isReviewUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("Review updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(
            @PathVariable("reviewId") Long reviewId
    ){
        boolean isReviewDeleted = reviewService.deletedReviewById(reviewId);
        if(isReviewDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not Deleted");
        }
    }
}
