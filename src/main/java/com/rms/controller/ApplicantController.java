// ApplicantController - Handles job application operations for applicants
// Demonstrates:
// - Session-based user tracking
// - Job application management
// - Application status tracking
// - Resume handling
// - Duplicate application prevention
package com.rms.controller;

import com.rms.model.ApplicationEntity;
import com.rms.model.JobEntity;
import com.rms.model.UserEntity;
import com.rms.service.ApplicationRepository;
import com.rms.service.AuthService;
import com.rms.service.JobService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private JobService jobService;

    // View all applications submitted by the applicant
    @GetMapping("/applications")
    public String viewMyApplications(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) return "redirect:/login";

        UserEntity user = authService.getUserByEmail(email);
        if (user == null) return "redirect:/login";

        List<ApplicationEntity> apps = applicationRepository.findByUser(user);

        model.addAttribute("applications", apps);
        model.addAttribute("username", user.getName()); // ✅ Show in navbar

        return "applicant-applications"; // or your HTML file name
    }

    // Submit application for a job
    @GetMapping("/apply/{jobId}")
    public String applyToJob(@PathVariable Long jobId,
                            @RequestParam String email) {
        UserEntity user = authService.getUserByEmail(email);
        JobEntity job = jobService.getJobById(jobId);

        if (user != null && job != null) {
            // Check for duplicate applications
            boolean alreadyApplied = applicationRepository.findByUserAndJob(user, job).isPresent();
            if (!alreadyApplied) {
                ApplicationEntity app = new ApplicationEntity(user, job);
                applicationRepository.save(app);

                // Handle resume submission
                String resume = user.getResume();
                System.out.println("Sending resume to recruiter: " + resume);
            }
        }

        return "redirect:/jobs/view?applied=true";
    }
}
