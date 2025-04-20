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

    @GetMapping("/dashboard/admin")
    public String adminDashboard() {
        return "dashboard-admin";
    }

    @GetMapping("/dashboard/applicant")
    public String applicantDashboard() {
        return "dashboard-applicant";
    }

    @GetMapping("/dashboard/recruiter")
    public String recruiterDashboard() {
        return "dashboard-recruiter";
    }

    @GetMapping("/dashboard/admin/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin-users";
    }
}
