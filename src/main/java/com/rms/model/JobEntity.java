// JobEntity - JPA Entity representing a job posting in the system
// Demonstrates:
// - JPA Entity mapping with table name specification
// - Entity relationships (postedBy links to recruiter)
// - Soft delete pattern (deletionRequested flag)
// - Encapsulation with getters/setters
// - Business domain modeling
package com.rms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Job posting details
    private String title;
    private String description;
    private String company;
    private double salary;

    // Recruiter reference (email or ID)
    private String postedBy;

    // Soft delete flag for job postings
    private boolean deletionRequested = false;

    // Default constructor required by JPA
    public JobEntity() {}

    // Parameterized constructor for job creation
    public JobEntity(String title, String description, String company, double salary, String postedBy) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.salary = salary;
        this.postedBy = postedBy;
    }

    // Getters & Setters - Encapsulation principle
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getPostedBy() { return postedBy; }
    public void setPostedBy(String postedBy) { this.postedBy = postedBy; }

    public boolean isDeletionRequested() { return deletionRequested; }
    public void setDeletionRequested(boolean deletionRequested) { this.deletionRequested = deletionRequested; }
}
