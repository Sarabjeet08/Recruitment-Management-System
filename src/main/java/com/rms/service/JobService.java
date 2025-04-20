// JobService - Service layer for job management operations
// Demonstrates:
// - Service Layer pattern (@Service)
// - Dependency Injection (@Autowired)
// - Repository pattern usage
// - Business logic encapsulation
// - CRUD operations delegation
package com.rms.service;

import com.rms.model.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    // Create operation - Post a new job
    public void postJob(JobEntity job) {
        jobRepository.save(job);
    }

    // Read operation - Get all jobs
    public List<JobEntity> getAllJobs() {
        return jobRepository.findAll();
    }

    // Read operation - Get job by ID
    public JobEntity getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }
}
