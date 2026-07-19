package com.samsung.jobms.job;

import com.samsung.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAllJobs();
    String createJob(Job job);
    Job findJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job job);
}
