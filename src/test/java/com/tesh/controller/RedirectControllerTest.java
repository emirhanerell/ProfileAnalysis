package com.tesh.controller;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class RedirectControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RedirectController redirectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(redirectController).build();
    }

    @Test
    void testRootRedirect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }

    @Test
    void testRedirectToAdminDashboard() throws Exception {
        Authentication authentication = mock(Authentication.class);
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        when(authentication.getAuthorities()).thenReturn((Collection)authorities);

        mockMvc.perform(get("/redirect").principal(authentication))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin-dashboard"));
    }

    @Test
    void testRedirectToUserDashboard() throws Exception {
        Authentication authentication = mock(Authentication.class);
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        when(authentication.getAuthorities()).thenReturn((Collection)authorities);

        mockMvc.perform(get("/redirect").principal(authentication))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }

    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("templates/login"));
    }

    @Test
    void testUserDashboard() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("templates/dashboard"));
    }

    @Test
    void testAdminDashboard() throws Exception {
        mockMvc.perform(get("/admin-dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("templates/admin-dashboard"));
    }
} 