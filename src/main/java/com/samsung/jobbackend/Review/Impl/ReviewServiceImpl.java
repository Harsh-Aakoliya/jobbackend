package com.samsung.jobbackend.Review.Impl;

import com.samsung.jobbackend.Review.Review;
import com.samsung.jobbackend.Review.ReviewRepository;
import com.samsung.jobbackend.Review.ReviewService;
import com.samsung.jobbackend.company.Company;
import com.samsung.jobbackend.company.CompanyRepository;
import com.samsung.jobbackend.company.CompanyService;
import com.samsung.jobbackend.company.Impl.CompanyServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    public List<Review> getReviewsByCompanyId(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    public String createReviewByCompanyId(Long id, Review review) {
        Company company = companyService.findCompanyById(id);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return "review created";
        }
        return "company with ID:" + id + " not found";
    }

    @Override
    public Review findReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = getReviewsByCompanyId(companyId);
        return reviews
                .stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReviewByid(Long companyId, Long reviewId, Review review) {
        if (companyService.findCompanyById(companyId) != null) {
            review.setCompany(companyService.findCompanyById(companyId));
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deletedReviewById(Long companyId, Long reviewId) {
        if (companyService.findCompanyById(companyId) != null && findReviewById(companyId,reviewId) != null) {
            reviewRepository.deleteById(reviewId);
            return true;
        } else {
            return false;
        }
    }

}