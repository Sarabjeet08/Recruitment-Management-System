// AuthService - Service layer for authentication and user management
// Demonstrates:
// - Service Layer pattern (@Service)
// - Dependency Injection (@Autowired)
// - Repository pattern usage
// - Business logic encapsulation
// - File handling operations
package com.rms.service;

import com.rms.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.File;
import java.io.IOException;

@Service
public class AuthService {

    // Inject UserRepository for data access
    @Autowired
    private UserRepository userRepository;

    // Validate user credentials
    public boolean validateUser(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    // Retrieve user by email
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Register new user with role and optional resume
    public void registerUser(String name, String email, String password, String role, String resume) {
        UserEntity user = new UserEntity(name, email, password, role, resume);
        userRepository.save(user);
    }

    // Get all users in the system
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Get user by ID
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    // Save or update user
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    // Delete user by ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Change user password with validation
    public boolean changePassword(String email, String oldPass, String newPass) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(oldPass)) {
            user.setPassword(newPass);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // Update recruiter profile information
    public boolean updateRecruiterProfile(String email, String username, String oldPass, String newPass) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(oldPass)) {
            user.setName(username);
            if (newPass != null && !newPass.isEmpty()) {
                user.setPassword(newPass);
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // Update applicant profile with resume file handling
    public boolean updateApplicantProfile(String email, String username, String oldPass, String newPass, MultipartFile resumeFile) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(oldPass)) {
            user.setName(username);
            if (newPass != null && !newPass.isEmpty()) {
                user.setPassword(newPass);
            }
            if (resumeFile != null && !resumeFile.isEmpty()) {
                // Handle resume file upload
                String resumePath = handleResumeUpload(resumeFile, email);
                user.setResume(resumePath);
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // Private helper method for resume file handling
    private String handleResumeUpload(MultipartFile file, String email) {
        // Implementation details for file handling
        return "resumes/" + email + "_" + file.getOriginalFilename();
    }
}
