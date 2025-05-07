package com.tesh.controller;

import com.tesh.Dtos.ChangePasswordRequest;
import com.tesh.Dtos.LoginRequest;
import com.tesh.Dtos.RegisterRequest;
import com.tesh.model.CustomUserDetails;
import com.tesh.model.Role;
import com.tesh.model.User;
import com.tesh.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService _userService;
    private final AuthenticationManager _authenticationManager;
    private final PasswordEncoder _passwordEncoder;

    public UserController(AuthenticationManager authenticationManager,UserService userService,PasswordEncoder passwordEncoder) {
        this._userService = userService;
        this._authenticationManager = authenticationManager;
        this._passwordEncoder = passwordEncoder;
    }

    @GetMapping("/create")
    public String create(Model model) {
        var userModel = new User();
        model.addAttribute("registerRequest", userModel);
        return "userCreate";
    }

    @PostMapping("/create")
    @ResponseBody
    public String register(@ModelAttribute RegisterRequest registerRequest, Model model) {

        if (_userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            var userModel = new User();
            model.addAttribute("errorMessage", "Email already exists!");
            model.addAttribute("registerRequest", userModel);
            return "userCreate";
        }

        var hashedPassword = _passwordEncoder.encode(registerRequest.getPassword());
        var role = Role.USER;
        _userService.registerUser(registerRequest.getEmail(),hashedPassword,role,registerRequest.getName());

        return "successfull";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@ModelAttribute LoginRequest loginRequest, HttpSession session) {
        Authentication authentication = _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var userDetails = (CustomUserDetails)authentication.getPrincipal();
        session.setAttribute("username",userDetails.getName());
        session.setAttribute("userid",userDetails.getId());
        session.setAttribute("userEmail",userDetails.getUsername());


        return "admin-dashboard";
    }


    @GetMapping("/index")
    public String index(Model model) {
        var userList = _userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "userIndex";
    }

    @GetMapping("/detail/{id}")
     public String detail(@PathVariable int id, Model model) {
        var user = _userService.findById(id);
        model.addAttribute("user", user);
        return "userDetail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        _userService.deleteUser(id);
        return "redirect:/user/index";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, Model model) {
        var user = _userService.findById(id);
        model.addAttribute("user", user);
        return "userUpdate";
    }

    @PostMapping("/update")
    public String update(@RequestBody RegisterRequest registerRequest) {
        var role = registerRequest.getRole().equals("ADMIN")  ? Role.ADMIN : Role.USER;
        _userService.updateUser(registerRequest.getId(),registerRequest.getEmail(),role,registerRequest.getName());
        return "redirect:/user/index";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute ChangePasswordRequest changePasswordRequest) {
        _userService.changePassword(changePasswordRequest.getId(),changePasswordRequest.getPassword());
        return "redirect:/user/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
