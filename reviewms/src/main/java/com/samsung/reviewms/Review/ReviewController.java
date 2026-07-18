package com.samsung.jobbackend.Review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    ReviewService reviewService;
    private ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviewsByCompanyId(
            @PathVariable("companyId") Long companyId
    ){
        List<Review> reviews = reviewService.getReviewsByCompanyId(companyId);
        return ResponseEntity.
                status((HttpStatus.OK)).
                body(reviews);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable("companyId") Long id,@RequestBody Review review){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.createReviewByCompanyId(id,review));
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(
            @PathVariable Long companyId,
            @PathVariable("reviewId") Long reviewId
    ){
        Review review=reviewService.findReviewById(companyId,reviewId);
        return ResponseEntity.
                status(HttpStatus.OK).
                body(review);//this review can be null if company with companyId do not exists
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewById(
            @PathVariable("companyId") Long companyId,
            @PathVariable("reviewId") Long reviewId,
            @RequestBody Review review
    ){
        boolean isReviewUpdated = reviewService.updateReviewByid(companyId,reviewId,review);
        if(isReviewUpdated){
            return ResponseEntity.status(HttpStatus.OK).body("Review updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(
            @PathVariable Long companyId,
            @PathVariable("reviewId") Long reviewId
    ){
        boolean isReviewDeleted = reviewService.deletedReviewById(companyId,reviewId);
        if(isReviewDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not Deleted");
        }
    }
}
