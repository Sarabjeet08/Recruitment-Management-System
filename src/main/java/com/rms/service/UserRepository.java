// UserRepository - Data access layer for user entities
// Demonstrates:
// - Repository pattern with custom query
// - Spring Data JPA integration
// - Email-based user lookup
// - Interface-based programming
// - Type-safe database operations
package com.rms.service;

import com.rms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Custom query method to find user by email
    // Used for authentication and user lookup
    UserEntity findByEmail(String email);
}
