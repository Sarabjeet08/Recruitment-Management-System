package com.rms.model;

public class Job {
    private int id;
    private String title;
    private String description;
    private String company;
    private double salary;

    public Job(int id, String title, String description, String company, double salary) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCompany() { return company; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "[ID: " + id + "] " + title + " at " + company + " - $" + salary + "\nDescription: " + description;
    }
}
