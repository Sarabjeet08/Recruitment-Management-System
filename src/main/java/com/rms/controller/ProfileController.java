// ProfileController - Handles user profile management
// Demonstrates:
// - Role-based profile views
// - Session management
// - File upload handling
// - Password management
// - Profile updates for different user types
package com.rms.controller;

import com.rms.model.UserEntity;
import com.rms.service.AuthService;
import com.rms.service.UserRepository;
import jakarta.servlet.http.HttpSession;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    // Inject required services
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    // Display profile view based on user role
    @GetMapping
    public String profileView(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) return "redirect:/login";

        UserEntity user = authService.getUserByEmail(email);
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        model.addAttribute("username", user.getName());

        // Route to appropriate view based on user role
        switch (user.getRole()) {
            case "admin":
                return "profile-admin";
            case "recruiter":
                return "profile-recruiter";
            case "applicant":
                return "profile-applicant";
            default:
                return "redirect:/login";
        }
    }

    // Update admin password
    @PostMapping("/admin/change-password")
    public String updateAdminPassword(@RequestParam String oldPassword,
                                      @RequestParam String newPassword,
                                      HttpSession session) {
        String email = (String) session.getAttribute("email");
        return authService.changePassword(email, oldPassword, newPassword)
            ? "redirect:/dashboard/admin"
            : "redirect:/profile?error";
    }

    // Update recruiter profile information
    @PostMapping("/recruiter/update")
    public String updateRecruiter(@RequestParam String username,
                                  @RequestParam String oldPassword,
                                  @RequestParam String newPassword,
                                  HttpSession session) {
        String email = (String) session.getAttribute("email");
        return authService.updateRecruiterProfile(email, username, oldPassword, newPassword)
            ? "redirect:/dashboard/recruiter"
            : "redirect:/profile?error";
    }

    // Update applicant profile information
    @PostMapping("/applicant/update-info")
    public String updateInfo(@RequestParam String username,
                             @RequestParam String oldPassword,
                             @RequestParam String newPassword,
                             HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) return "redirect:/login";

        UserEntity user = authService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setName(username);
            user.setPassword(newPassword);
            userRepository.save(user);
            return "redirect:/dashboard/applicant";
        }

        return "redirect:/profile?error";
    }

    // Handle resume file upload for applicants
    @PostMapping("/applicant/upload-resume")
    public String uploadResume(@RequestParam("resume") MultipartFile resumeFile,
                               HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) return "redirect:/login";
    
        UserEntity user = authService.getUserByEmail(email);
    
        if (user != null && !resumeFile.isEmpty()) {
            try {
                // Generate unique filename
                String fileName = System.currentTimeMillis() + "_" + resumeFile.getOriginalFilename().replaceAll("\\s+", "_");
    
                // Validate file type
                if (fileName.toLowerCase().endsWith(".pdf") || fileName.toLowerCase().endsWith(".doc") || fileName.toLowerCase().endsWith(".docx")) {
    
                    // Create upload directory if it doesn't exist
                    String uploadDirPath = System.getProperty("user.dir") + "/uploads/resumes/";
                    File uploadDir = new File(uploadDirPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();
    
                    // Save file and update user's resume path
                    String fullPath = uploadDirPath + fileName;
                    resumeFile.transferTo(new File(fullPath));
                    user.setResume("/uploads/resumes/" + fileName);
                    userRepository.save(user);
    
                    System.out.println("✅ Resume uploaded: " + fullPath);
                } else {
                    System.out.println("❌ Invalid file type: " + fileName);
                }
            } catch (Exception e) {
                System.out.println("❌ Resume upload failed:");
                e.printStackTrace();
            }
        }
    
        return "redirect:/profile";
    }
}    