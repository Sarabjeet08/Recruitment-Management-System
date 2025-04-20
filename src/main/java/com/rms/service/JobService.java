package com.rms.service;

import com.rms.model.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public void postJob(JobEntity job) {
        jobRepository.save(job);
    }

    public List<JobEntity> getAllJobs() {
        return jobRepository.findAll();
    }
    public JobEntity getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }
    
}
