// DashboardController - Handles dashboard views for different user roles
// Demonstrates:
// - Role-based view routing
// - Spring MVC Controller pattern
// - Dependency Injection
// - Model-View pattern
// - Role-specific dashboard rendering
package com.rms.controller;

import com.rms.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    // Admin dashboard view
    @GetMapping("/dashboard/admin")
    public String adminDashboard() {
        return "dashboard-admin";
    }

    // Applicant dashboard view
    @GetMapping("/dashboard/applicant")
    public String applicantDashboard() {
        return "dashboard-applicant";
    }

    // Recruiter dashboard view
    @GetMapping("/dashboard/recruiter")
    public String recruiterDashboard() {
        return "dashboard-recruiter";
    }

    // Admin user management view
    @GetMapping("/dashboard/admin/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin-users";
    }
}
