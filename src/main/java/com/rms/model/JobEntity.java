package com.rms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String company;
    private double salary;

    // Email or ID of recruiter who posted the job
    private String postedBy;

    // True if recruiter requested deletion
    private boolean deletionRequested = false;

    public JobEntity() {}

    public JobEntity(String title, String description, String company, double salary, String postedBy) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.salary = salary;
        this.postedBy = postedBy;
    }

    // Getters & Setters

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
