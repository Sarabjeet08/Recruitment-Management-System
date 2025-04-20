// JobController - Handles job posting and application management
// Demonstrates:
// - RESTful URL mapping (@RequestMapping)
// - Dependency Injection (@Autowired)
// - Session management
// - Model-View pattern
// - Business logic delegation to services
package com.rms.controller;

import com.rms.model.JobEntity;
import com.rms.model.ApplicationEntity;
import com.rms.service.JobService;
import com.rms.service.ApplicationRepository;
import com.rms.service.AuthService;
import com.rms.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AuthService authService;

    // Job posting form display
    @GetMapping("/post")
    public String showPostForm() {
        return "post-job";
    }

    // Job submission endpoint
    @PostMapping("/submit")
    public String postJob(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String company,
                          @RequestParam double salary,
                          @RequestParam String recruiterEmail) {
        JobEntity job = new JobEntity(title, description, company, salary, recruiterEmail);
        jobService.postJob(job);
        return "redirect:/dashboard/recruiter";
    }

    // View all jobs with application status
    @GetMapping("/view")
    public String viewJobs(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");

        if (email == null) {
            System.out.println("❌ Session email missing");
            return "redirect:/login";
        }

        UserEntity user = authService.getUserByEmail(email);
        if (user == null) {
            System.out.println("❌ No user found for email: " + email);
            return "redirect:/login";
        }

        // Fetch jobs and user's applications
        List<JobEntity> jobs = jobService.getAllJobs();
        List<ApplicationEntity> applications = applicationRepository.findByUser(user);

        // Create map of applied jobs for quick lookup
        Map<Long, ApplicationEntity> appliedJobMap = new HashMap<>();
        for (ApplicationEntity app : applications) {
            appliedJobMap.put(app.getJob().getId(), app);
        }

        // Add data to model for view rendering
        model.addAttribute("jobs", jobs);
        model.addAttribute("userEmail", email);
        model.addAttribute("username", user.getName());
        model.addAttribute("appliedJobMap", appliedJobMap);

        return "view-jobs";
    }

    // Job application submission
    @GetMapping("/apply/{jobId}")
    public String applyToJob(@PathVariable Long jobId, HttpSession session) {
        String email = (String) session.getAttribute("email");

        if (email == null || email.isBlank()) {
            System.out.println("❌ Email is missing from session");
            return "redirect:/login";
        }

        UserEntity user = authService.getUserByEmail(email);
        JobEntity job = jobService.getJobById(jobId);

        if (user != null && job != null) {
            // Check for existing application
            Optional<ApplicationEntity> existingApp = applicationRepository.findByUserAndJob(user, job);

            if (existingApp.isPresent()) {
                ApplicationEntity app = existingApp.get();
                String status = app.getStatus();

                // Handle re-application for rejected jobs
                if ("Rejected".equalsIgnoreCase(status)) {
                    app.setStatus("Pending");
                    applicationRepository.save(app);
                    System.out.println("✅ Re-applied to job: " + jobId);
                } else {
                    System.out.println("⚠️ Already applied to job with status: " + status);
                }
            } else {
                // Create new application
                ApplicationEntity newApp = new ApplicationEntity(user, job);
                newApp.setStatus("Pending");
                applicationRepository.save(newApp);
                System.out.println("✅ New application submitted for job: " + jobId);
            }
        } else {
            System.out.println("❌ Invalid user or job");
        }

        return "redirect:/jobs/view";
    }
}