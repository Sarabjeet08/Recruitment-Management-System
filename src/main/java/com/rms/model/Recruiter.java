package com.rms.model;

public class Recruiter extends User {
    public Recruiter(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "Recruiter: " + super.toString();
    }
}
