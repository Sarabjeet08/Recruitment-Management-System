// User - Abstract base class for all user types in the system
// Demonstrates:
// - Abstract class definition
// - Encapsulation with private fields
// - Getter methods
// - Constructor initialization
// - Method overriding
// - Inheritance hierarchy foundation
package com.rms.model;

public abstract class User {
    // Common attributes for all user types
    private String id;
    private String name;
    private String email;
    private String password;

    // Constructor to initialize common user attributes
    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter methods - Encapsulation principle
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Override toString for common user information
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
