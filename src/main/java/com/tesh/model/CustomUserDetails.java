package com.tesh.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String email;
    @Getter
    private String name;
    @Getter
    private int Id;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, String email, String name,int Id,Collection<?
            extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.Id = Id;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
