package com.rms.model;

public class Administrator extends User {
    public Administrator(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "Administrator: " + super.toString();
    }
}
