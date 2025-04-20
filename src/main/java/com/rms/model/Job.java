// Job - Model class representing a job posting
// Demonstrates:
// - Encapsulation with private fields
// - Constructor initialization
// - Getter methods
// - Method overriding
// - String formatting
package com.rms.model;

public class Job {
    // Job posting attributes
    private int id;
    private String title;
    private String description;
    private String company;
    private double salary;

    // Constructor to initialize job details
    public Job(int id, String title, String description, String company, double salary) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.salary = salary;
    }

    // Getter methods - Encapsulation principle
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCompany() { return company; }
    public double getSalary() { return salary; }

    // Override toString for formatted job information
    @Override
    public String toString() {
        return "[ID: " + id + "] " + title + " at " + company + " - $" + salary + "\nDescription: " + description;
    }
}
