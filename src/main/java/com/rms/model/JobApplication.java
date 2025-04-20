package com.rms.model;

public class JobApplication {
    private int id;
    private Applicant applicant;
    private Job job;
    private String status;

    public JobApplication(int id, Applicant applicant) {
        this.id = id;
        this.applicant = applicant;
        this.status = "Pending";
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application ID: " + id +
               ", Applicant: " + (applicant != null ? applicant.getName() : "None") +
               ", Job: " + (job != null ? job.getId() + " - " + job.toString() : "None") +
               ", Status: " + status;
    }
}
