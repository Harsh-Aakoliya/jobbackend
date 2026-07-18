package com.samsung.reviewms.Review;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    public List<Review> getReviewsByCompanyId(Long companyId);
    String createReviewByCompanyId(Long id,Review review);
    Review findReviewById(Long reviewId);
    boolean updateReviewByid(Long reviewId, Review review);
    boolean deletedReviewById(Long reviewId);
}
