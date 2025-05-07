package com.tesh.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model, Authentication authentication) {
        Object statusCode = request.getAttribute("jakarta.servlet.error.status_code");

        boolean isAdmin = authentication != null &&
                authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);

        if (statusCode != null) {
            int status = Integer.parseInt(statusCode.toString());
            if (status == 404) {
                return "error/403"; // 404.html
            } else if (status == 403) {
                return "error/403"; // 403.html
            }
        }
        return "error/403"; // fallback sayfa
    }
}
