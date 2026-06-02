package com.samsung.jobbackend.job.impl;

import com.samsung.jobbackend.job.Job;
import com.samsung.jobbackend.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    List<Job> jobs = new ArrayList<>();
    private Long id=1L;

    @Override
    public List<Job> findAllJobs() {
        return jobs;
    }

    @Override
    public String createJob(Job job) {
        job.setId(id++);
        jobs.add(job);
        return "Job added successfully";
    }

    @Override
    public Job findJobById(Long id) {
        Job existingJob = jobs.stream().filter(j -> j.getId().equals(id)).findFirst().orElse(null);
        return existingJob;
    }

    @Override
    public Job deleteJobById(Long id) {
        Job existingJob = jobs.stream().filter(j -> j.getId().equals(id)).findFirst().orElse(null);
        if(existingJob != null){
            jobs.remove(existingJob);
            return existingJob;
        }
        return null;
    }

    @Override
    public Job updateJobById(Long id, Job job) {
        Job existingJob = findJobById(id);

        if(existingJob != null){
            System.out.println("here");
            jobs.remove(existingJob);
            existingJob = job;
            existingJob.setId(id);
            jobs.add(existingJob);
            return existingJob;
        }
        return null;
    }
}
