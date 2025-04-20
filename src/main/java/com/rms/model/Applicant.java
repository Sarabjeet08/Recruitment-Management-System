package com.rms.model;

public class Applicant extends User {
    private String resume;

    public Applicant(String id, String name, String email, String password, String resume) {
        super(id, name, email, password);
        this.resume = resume;
    }

    public String getResume() {
        return resume;
    }

    @Override
    public String toString() {
        return "Applicant: " + super.toString() + ", Resume: " + resume;
    }
}
