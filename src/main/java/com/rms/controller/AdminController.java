package com.rms.controller;

import com.rms.model.JobEntity;
import com.rms.model.UserEntity;
import com.rms.model.ApplicationEntity;
import com.rms.service.JobRepository;
import com.rms.service.ApplicationRepository;
import com.rms.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AuthService authService;

    // ✅ View all jobs (including deletion requests)
    @GetMapping("/jobs")
    public String listAllJobs(Model model) {
        List<JobEntity> jobs = jobRepository.findAll();
        model.addAttribute("jobs", jobs);
        return "admin-jobs";
    }

    // ✅ View all applicants for a specific job
    @GetMapping("/jobs/applicants/{jobId}")
    public String viewApplicants(@PathVariable Long jobId, Model model) {
        List<ApplicationEntity> applications = applicationRepository.findByJobId(jobId);
        model.addAttribute("applications", applications);
        return "admin-job-applicants";
    }

    // ✅ Confirm delete: Admin deletes job after recruiter requests
    @GetMapping("/jobs/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        // Delete applications first
        List<ApplicationEntity> apps = applicationRepository.findByJobId(id);
        applicationRepository.deleteAll(apps);

        // Then delete the job
        jobRepository.deleteById(id);
        return "redirect:/admin/jobs";
    }

    // ✅ Cancel deletion request from recruiter
    @GetMapping("/jobs/cancel-delete/{id}")
    public String cancelDeleteRequest(@PathVariable Long id) {
        JobEntity job = jobRepository.findById(id).orElse(null);
        if (job != null && job.isDeletionRequested()) {
            job.setDeletionRequested(false);
            jobRepository.save(job);
        }
        return "redirect:/admin/jobs";
    }

    // ✅ View all users (for role management)
    @GetMapping("/users")
    public String viewAllUsers(Model model) {
        List<UserEntity> users = authService.getAllUsers(); // Make sure this method exists
        model.addAttribute("users", users);
        return "admin-users";
    }

    // ✅ Update role of a specific user
    @PostMapping("/users/update-role/{id}")
    public String updateUserRole(@PathVariable Long id, @RequestParam String role) {
        UserEntity user = authService.getUserById(id);
        if (user != null) {
            user.setRole(role);
            authService.saveUser(user); // Save updated user
        }
        return "redirect:/admin/users";
    }

    // ✅ Delete a user
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        authService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}
