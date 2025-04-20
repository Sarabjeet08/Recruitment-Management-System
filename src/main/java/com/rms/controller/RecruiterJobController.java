// RecruiterJobController - Handles job posting and applicant management for recruiters
// Demonstrates:
// - Job CRUD operations
// - Application status management
// - Soft delete pattern
// - Model attribute binding
// - Path variable handling
package com.rms.controller;

import com.rms.model.JobEntity;
import com.rms.model.ApplicationEntity;
import com.rms.service.JobRepository;
import com.rms.service.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard/recruiter")
public class RecruiterJobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    // View all jobs posted by the recruiter
    @GetMapping("/jobs")
    public String showRecruiterJobs(Model model) {
        List<JobEntity> myJobs = jobRepository.findAll();
        model.addAttribute("myJobs", myJobs);
        return "recruiter-jobs";
    }

    // View applicants for a specific job
    @GetMapping("/applicants/{jobId}")
    public String viewApplicants(@PathVariable Long jobId, Model model) {
        List<ApplicationEntity> applications = applicationRepository.findByJobId(jobId);
        model.addAttribute("applications", applications);
        return "recruiter-applicants";
    }

    // Request job deletion (requires admin approval)
    @PostMapping("/request-delete/{jobId}")
    public String requestDelete(@PathVariable Long jobId) {
        JobEntity job = jobRepository.findById(jobId).orElse(null);
        if (job != null) {
            job.setDeletionRequested(true);
            jobRepository.save(job);
        }
        return "redirect:/dashboard/recruiter/jobs";
    }

    // Display job edit form
    @GetMapping("/edit/{jobId}")
    public String showEditForm(@PathVariable Long jobId, Model model) {
        JobEntity job = jobRepository.findById(jobId).orElse(null);
        if (job != null) {
            model.addAttribute("job", job);
            return "edit-job";
        }
        return "redirect:/dashboard/recruiter/jobs";
    }

    // Update job details
    @PostMapping("/edit/{jobId}")
    public String updateJob(@PathVariable Long jobId, @ModelAttribute JobEntity updatedJob) {
        JobEntity job = jobRepository.findById(jobId).orElse(null);
        if (job != null) {
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setCompany(updatedJob.getCompany());
            job.setSalary(updatedJob.getSalary());
            jobRepository.save(job);
        }
        return "redirect:/dashboard/recruiter/jobs";
    }

    // Update application status (e.g., Accept/Reject)
    @PostMapping("/applications/update/{applicationId}")
    public String updateApplicationStatus(@PathVariable Long applicationId,
                                        @RequestParam String status,
                                        @RequestParam Long jobId) {
        ApplicationEntity app = applicationRepository.findById(applicationId).orElse(null);
        if (app != null) {
            app.setStatus(status);
            applicationRepository.save(app);
        }
        return "redirect:/dashboard/recruiter/applicants/" + jobId;
    }
}
