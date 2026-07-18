package com.samsung.jobbackend.Review;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    public List<Review> getReviewsByCompanyId(Long companyId);
    String createReviewByCompanyId(Long id,Review review);
    Review findReviewById(Long companyId, Long reviewId);
    boolean updateReviewByid(Long companyId, Long reviewId, Review review);
    boolean deletedReviewById(Long companyId, Long reviewId);
}
