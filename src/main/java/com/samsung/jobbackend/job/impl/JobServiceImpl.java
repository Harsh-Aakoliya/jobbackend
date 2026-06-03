package com.samsung.jobbackend.job.impl;

import com.samsung.jobbackend.job.Job;
import com.samsung.jobbackend.job.JobRepository;
import com.samsung.jobbackend.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public String createJob(Job job) {
        jobRepository.save(job);
        return "Job added successfully";
    }

    @Override
    public Job findJobById(Long id) {
        Job existingJob = jobRepository.findById(id).orElse(null);
        return existingJob;
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
