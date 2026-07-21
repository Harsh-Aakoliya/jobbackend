package com.samsung.jobms.job.mapper;

import com.samsung.jobms.job.Job;
import com.samsung.jobms.job.dto.JobWithCompanyReviewDTO;
import com.samsung.jobms.job.external.Company;
import com.samsung.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobWithCompanyReviewDTO mapToJobWithCompanyDto(Job job, Company company, List<Review> reviews){
        JobWithCompanyReviewDTO jobWithCompanyReviewDTO = new JobWithCompanyReviewDTO();
        jobWithCompanyReviewDTO.setId(job.getId());
        jobWithCompanyReviewDTO.setDescription(job.getDescription());
        jobWithCompanyReviewDTO.setTitle(job.getTitle());
        jobWithCompanyReviewDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyReviewDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyReviewDTO.setLocation(job.getLocation());
        jobWithCompanyReviewDTO.setCompany(company);
        jobWithCompanyReviewDTO.setReviews(reviews);
        return jobWithCompanyReviewDTO;
    }
}
