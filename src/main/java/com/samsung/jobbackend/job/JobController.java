package com.samsung.jobbackend.job;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
    List<Job> jobs = new ArrayList<>();
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }
    @PostMapping("/job")
    public ResponseEntity<String> saveJob(@RequestBody Job job){
        jobs.add(job);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job added successfull");
    }
    @GetMapping("/job")
    public ResponseEntity<Job> findJobByIdUsingRequestParam(@RequestParam Long jobId){
        Job existingJob = jobs.stream().filter(j -> j.getId().equals(jobId)).findFirst().orElse(null);
        if(existingJob == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(existingJob);
        }
    }
    @GetMapping("/job/{job_id}")
    public ResponseEntity<Job> findJobByIdUsingPathvariable(@PathVariable("job_id") Long jobId){
        Job existingJob = jobs.stream().filter(j -> j.getId().equals(jobId)).findFirst().orElse(null);
        if(existingJob == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(existingJob);
        }
    }
}
