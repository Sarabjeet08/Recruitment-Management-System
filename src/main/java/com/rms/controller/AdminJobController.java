// AdminJobController - Handles job application management for administrators
// Demonstrates:
// - Application review functionality
// - Path variable handling
// - Model-View pattern
// - Repository pattern usage
package com.rms.controller;

import com.rms.model.ApplicationEntity;
import com.rms.service.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard/admin")
public class AdminJobController {

    // Inject application repository for data access
    @Autowired
    private ApplicationRepository applicationRepository;

    // View applicants for a specific job
    @GetMapping("/applicants/{jobId}")
    public String viewApplicants(@PathVariable Long jobId, Model model) {
        // Retrieve all applications for the specified job
        List<ApplicationEntity> applicants = applicationRepository.findByJobId(jobId);
        model.addAttribute("applications", applicants);
        return "admin-applicants";
    }
}
