// JobApplication - Model class representing a job application
// Demonstrates:
// - Object composition (Applicant and Job)
// - Encapsulation with private fields
// - State management
// - Null safety in toString
// - Method chaining
package com.rms.model;

public class JobApplication {
    // Application attributes
    private int id;
    private Applicant applicant;
    private Job job;
    private String status;

    // Constructor initializes application with applicant
    public JobApplication(int id, Applicant applicant) {
        this.id = id;
        this.applicant = applicant;
        this.status = "Pending";
    }

    // Set job for the application
    public void setJob(Job job) {
        this.job = job;
    }

    // Update application status
    public void updateStatus(String status) {
        this.status = status;
    }

    // Override toString with null-safe string concatenation
    @Override
    public String toString() {
        return "Application ID: " + id +
               ", Applicant: " + (applicant != null ? applicant.getName() : "None") +
               ", Job: " + (job != null ? job.getId() + " - " + job.toString() : "None") +
               ", Status: " + status;
    }
}
