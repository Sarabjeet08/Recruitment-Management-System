// ApplicationEntity - JPA Entity representing a job application
// Demonstrates:
// - JPA Entity relationships (@ManyToOne)
// - Eager fetching strategy
// - Composite key pattern (user_id + job_id)
// - Application state management
// - Entity lifecycle
package com.rms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship with User
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // Many-to-One relationship with Job
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id", nullable = false)
    private JobEntity job;

    // Application status (Pending, Accepted, Rejected)
    private String status;

    // Default constructor required by JPA
    public ApplicationEntity() {}

    // Parameterized constructor for new applications
    public ApplicationEntity(UserEntity user, JobEntity job) {
        this.user = user;
        this.job = job;
        this.status = "Pending";
    }

    // Getters and Setters - Encapsulation principle
    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
