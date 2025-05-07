package com.tesh.service;

import com.tesh.model.CustomUserDetails;
import com.tesh.model.User;
import com.tesh.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository _userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this._userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = _userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user.getEmail(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getId(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())));
    }
}
