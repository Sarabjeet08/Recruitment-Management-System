// ApplicationRepository - Data access layer for job applications
// Demonstrates:
// - Repository pattern with custom queries
// - Spring Data JPA query methods
// - Relationship-based queries
// - Optional return type for null safety
// - Composite key queries
package com.rms.service;

import com.rms.model.ApplicationEntity;
import com.rms.model.JobEntity;
import com.rms.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
    // Find all applications by user
    List<ApplicationEntity> findByUser(UserEntity user);

    // Find all applications for a specific job
    List<ApplicationEntity> findByJobId(Long jobId);

    // Find specific application by user and job (for duplicate prevention)
    Optional<ApplicationEntity> findByUserAndJob(UserEntity user, JobEntity job);
}
