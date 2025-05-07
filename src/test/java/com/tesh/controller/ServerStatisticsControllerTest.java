package com.tesh.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tesh.model.ServerDetails;
import com.tesh.service.ServerStatisticService;

class ServerStatisticsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ServerStatisticService serverStatisticService;

    @InjectMocks
    private ServerStatisticsController serverStatisticsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(serverStatisticsController).build();
    }

    @Test
    void testGetServerStatistics() throws Exception {
        // Prepare test data
        ServerDetails server1 = new ServerDetails();
        ServerDetails server2 = new ServerDetails();
        List<ServerDetails> servers = Arrays.asList(server1, server2);

        // Configure mock behavior
        when(serverStatisticService.getAllServerDetails()).thenReturn(servers);

        // Execute and verify
        mockMvc.perform(get("/serverStatistics/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("serverMetricIndex"))
                .andExpect(model().attributeExists("serverDetailsList"))
                .andExpect(model().attribute("serverDetailsList", servers));
    }
} 