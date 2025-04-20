// Recruiter - Model class representing job recruiters
// Demonstrates:
// - Inheritance from User class
// - Role-specific user type
// - Method overriding
// - Constructor chaining
// - Polymorphism
package com.rms.model;

public class Recruiter extends User {
    // Constructor that chains to parent class constructor
    public Recruiter(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    // Override toString to include role-specific information
    @Override
    public String toString() {
        return "Recruiter: " + super.toString();
    }
}
