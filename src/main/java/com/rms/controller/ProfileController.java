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

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String profileView(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) return "redirect:/login";

        UserEntity user = authService.getUserByEmail(email);
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        model.addAttribute("username", user.getName());

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

    @PostMapping("/admin/change-password")
    public String updateAdminPassword(@RequestParam String oldPassword,
                                      @RequestParam String newPassword,
                                      HttpSession session) {
        String email = (String) session.getAttribute("email");
        return authService.changePassword(email, oldPassword, newPassword)
            ? "redirect:/dashboard/admin"
            : "redirect:/profile?error";
    }

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

    @PostMapping("/applicant/upload-resume")
    public String uploadResume(@RequestParam("resume") MultipartFile resumeFile,
                               HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) return "redirect:/login";
    
        UserEntity user = authService.getUserByEmail(email);
    
        if (user != null && !resumeFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + resumeFile.getOriginalFilename().replaceAll("\\s+", "_");
    
                if (fileName.toLowerCase().endsWith(".pdf") || fileName.toLowerCase().endsWith(".doc") || fileName.toLowerCase().endsWith(".docx")) {
    
                    // ✅ Store absolute path (portable)
                    String uploadDirPath = System.getProperty("user.dir") + "/uploads/resumes/";
                    File uploadDir = new File(uploadDirPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();
    
                    String fullPath = uploadDirPath + fileName;
                    resumeFile.transferTo(new File(fullPath));
    
                    // ✅ Save relative path to DB
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