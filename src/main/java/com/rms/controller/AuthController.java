package com.rms.controller;

import com.rms.model.UserEntity;
import com.rms.service.AuthService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping({"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/login")
public String handleLogin(@RequestParam String email, @RequestParam String password,
                          Model model, HttpSession session) {
    if (authService.validateUser(email, password)) {
        UserEntity user = authService.getUserByEmail(email); 

        // ✅ Store session values so ProfileController can access them
        session.setAttribute("email", user.getEmail());
        session.setAttribute("username", user.getName());

        return "redirect:/dashboard/" + user.getRole();
    } else {
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }
}

    

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam(required = false) String resume
    ) {
        authService.registerUser(name, email, password, role, resume);
        return "redirect:/login";
    }
}
