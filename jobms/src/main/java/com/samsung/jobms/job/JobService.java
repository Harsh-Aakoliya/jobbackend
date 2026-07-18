package com.samsung.jobms.job;

import java.util.List;

public interface JobService {
    List<Job> findAllJobs();
    String createJob(Job job);
    Job findJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job job);
}
