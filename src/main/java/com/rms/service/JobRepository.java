// JobRepository - Data access layer for job entities
// Demonstrates:
// - Repository pattern implementation
// - Spring Data JPA integration
// - Interface-based programming
// - CRUD operations inheritance
// - Type-safe database operations
package com.rms.service;

import com.rms.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Extends JpaRepository to inherit CRUD operations
// Generic types: JobEntity (entity type) and Long (ID type)
public interface JobRepository extends JpaRepository<JobEntity, Long> {}
