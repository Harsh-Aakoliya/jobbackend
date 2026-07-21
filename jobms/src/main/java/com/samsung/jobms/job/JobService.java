package com.samsung.jobms.job;

import com.samsung.jobms.job.dto.JobWithCompanyReviewDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyReviewDTO> findAllJobs();
    String createJob(Job job);
    JobWithCompanyReviewDTO findJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job job);
}
