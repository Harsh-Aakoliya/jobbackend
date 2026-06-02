package com.samsung.jobbackend.job;

import java.util.List;

public interface JobService {
    List<Job> findAllJobs();
    String createJob(Job job);
    Job findJobById(Long id);
    Job deleteJobById(Long id);
    Job updateJobById(Long id, Job job);
}
