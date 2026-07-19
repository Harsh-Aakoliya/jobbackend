package com.samsung.jobms.job;

import com.samsung.jobms.job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    JobService jobService;
    JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping("/jobs")
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(jobService.findAllJobs());
    }
    @PostMapping("/job")
    public ResponseEntity<String> saveJob(@RequestBody Job job){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(job));
    }
    @GetMapping("/job")
    public ResponseEntity<Job> findJobByIdUsingRequestParam(@RequestParam Long jobId){
        Job existingJob = jobService.findJobById(jobId);
        if(existingJob == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existingJob);
    }
    @GetMapping("/job/{job_id}")
    public ResponseEntity<Job> findJobByIdUsingPathvariable(@PathVariable("job_id") Long jobId){
        Job existingJob = jobService.findJobById(jobId);
        if(existingJob == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existingJob);
    }


    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJobByid(@PathVariable("id") Long jobId){
        boolean isDeleted = jobService.deleteJobById(jobId);
        System.out.println("isdeleted: " + isDeleted);
        if(!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Job deleted successfully");
    }

    @PutMapping("/job/{id}")
    public ResponseEntity<String> updateJob(@PathVariable("id") Long id, @RequestBody Job job){
        boolean isJobUpdated=jobService.updateJobById(id,job);
        if(!isJobUpdated){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("job updated successfully");
    }
}
