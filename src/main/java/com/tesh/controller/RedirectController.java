package com.tesh.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }

    @GetMapping("/redirect")
    public String redirectBasedOnRole(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin-dashboard";
        } else {
            return "redirect:/dashboard";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "templates/login";
    }

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "templates/dashboard";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }
}
