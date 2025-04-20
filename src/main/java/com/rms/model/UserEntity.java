// UserEntity class - JPA Entity representing a user in the system
// Demonstrates:
// - JPA Entity mapping (@Entity, @Table)
// - Encapsulation (private fields with public getters/setters)
// - JPA Identity generation (@Id, @GeneratedValue)
// - Constructor overloading (default and parameterized constructors)
package com.rms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role;
    private String resume;

    // Default constructor required by JPA
    public UserEntity() {}

    // Parameterized constructor for object initialization
    public UserEntity(String name, String email, String password, String role, String resume) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.resume = resume;
    }

    // Getters and Setters - Encapsulation principle
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getResume() { return resume; }
    public String getUsername() {  return this.name; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setResume(String resume) { this.resume = resume; }
}
