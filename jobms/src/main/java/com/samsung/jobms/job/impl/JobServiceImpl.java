package com.samsung.jobms.job.impl;

import com.samsung.jobms.job.Job;
import com.samsung.jobms.job.JobRepository;
import com.samsung.jobms.job.JobService;
import com.samsung.jobms.job.dto.JobWithCompanyDTO;
import com.samsung.jobms.job.external.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public List<JobWithCompanyDTO> findAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        for(Job job: jobs){
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            Company company = restTemplate.getForObject("http://localhost:8081/companies/"+job.getCompanyId(),Company.class);
            jobWithCompanyDTO.setCompany(company);
            jobWithCompanyDTO.setJob(job);

            jobWithCompanyDTOS.add(jobWithCompanyDTO);
        }
        return jobWithCompanyDTOS;
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
