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

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/applicants/{jobId}")
    public String viewApplicants(@PathVariable Long jobId, Model model) {
        List<ApplicationEntity> applicants = applicationRepository.findByJobId(jobId);
        model.addAttribute("applications", applicants);
        return "admin-applicants";
    }
}
