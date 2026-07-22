package com.samsung.jobms.job.impl;

import com.samsung.jobms.job.Job;
import com.samsung.jobms.job.JobRepository;
import com.samsung.jobms.job.JobService;
import com.samsung.jobms.job.clients.CompanyClient;
import com.samsung.jobms.job.clients.ReviewClient;
import com.samsung.jobms.job.dto.JobWithCompanyReviewDTO;
import com.samsung.jobms.job.external.Company;
import com.samsung.jobms.job.external.Review;
import com.samsung.jobms.job.mapper.JobMapper;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    CompanyClient companyClient;
    ReviewClient reviewClient;
    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Autowired
    RestTemplate restTemplate;

    public JobWithCompanyReviewDTO convertToDto(Job job){
        Company company = companyClient.getCompany(job.getId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobWithCompanyReviewDTO jobWithCompanyReviewDTO = JobMapper.mapToJobWithCompanyDto(job, company, reviews);
        return jobWithCompanyReviewDTO;
    }
    @Override
    public List<JobWithCompanyReviewDTO> findAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyReviewDTO> jobWithCompanyReviewDTOS = new ArrayList<>();
        for(Job job: jobs){
            jobWithCompanyReviewDTOS.add(convertToDto(job));
        }
        return jobWithCompanyReviewDTOS;
    }

    @Override
    public String createJob(Job job) {
        jobRepository.save(job);
        //here we can or cannot receive a company
        //if we are not receiving company then it's ok here it will be ignored and on findingalljob we will get null for that job
        //but if we are receving the company and if that company with provided id do not exists in company table then it will give
        //referential integraty error
        //so for that case here we need to make api call first if company with that received id do not exists

        //but as of now we are assuming that company already exists
        return "Job added successfully";
    }

    @Override
    public JobWithCompanyReviewDTO findJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        if(!jobRepository.existsById(id)){
            return false;
        }
        jobRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateJobById(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job existingJob = jobOptional.get();
            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setLocation(job.getLocation());
            existingJob.setMinSalary(job.getMinSalary());
            existingJob.setMaxSalary(job.getMaxSalary());
            jobRepository.save(existingJob);
            return true;
        }
        return false;
    }
}
