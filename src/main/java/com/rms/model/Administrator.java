// Administrator - Model class representing system administrators
// Demonstrates:
// - Inheritance from User class
// - Role-specific user type
// - Method overriding
// - Constructor chaining
// - Polymorphism
package com.rms.model;

public class Administrator extends User {
    // Constructor that chains to parent class constructor
    public Administrator(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    // Override toString to include role-specific information
    @Override
    public String toString() {
        return "Administrator: " + super.toString();
    }
}
