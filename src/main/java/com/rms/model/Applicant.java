// Applicant - Model class representing job applicants
// Demonstrates:
// - Inheritance from User class
// - Additional attribute (resume)
// - Encapsulation with getter
// - Method overriding
// - Constructor chaining
package com.rms.model;

public class Applicant extends User {
    // Additional attribute specific to applicants
    private String resume;

    // Constructor that chains to parent and initializes resume
    public Applicant(String id, String name, String email, String password, String resume) {
        super(id, name, email, password);
        this.resume = resume;
    }

    // Getter for resume - Encapsulation principle
    public String getResume() {
        return resume;
    }

    // Override toString to include resume information
    @Override
    public String toString() {
        return "Applicant: " + super.toString() + ", Resume: " + resume;
    }
}
