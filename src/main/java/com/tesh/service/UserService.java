package com.tesh.service;

import com.tesh.model.Role;
import com.tesh.model.User;
import com.tesh.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String email, String password, Role role,String name) {
        User user = User.builder()
                .email(email)
                .password(password)
                .role(role)
                .name(name)
                .build();
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void updateUser(int id, String email, Role role, String name) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setEmail(email);
            user.setRole(role);
            user.setName(name);
            userRepository.save(user);
        }
    }

    public void changePassword(int id, String password) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }
    }
}
