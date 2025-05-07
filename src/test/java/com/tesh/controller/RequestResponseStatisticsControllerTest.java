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

import com.tesh.model.RequestResponseLog;
import com.tesh.service.RequestResponseStatisticService;

class RequestResponseStatisticsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RequestResponseStatisticService requestResponseStatisticService;

    @InjectMocks
    private RequestResponseStatisticsController requestResponseStatisticsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(requestResponseStatisticsController).build();
    }

    @Test
    void testGetRequestResponseStatistics() throws Exception {
        // Prepare test data
        RequestResponseLog log1 = new RequestResponseLog();
        RequestResponseLog log2 = new RequestResponseLog();
        List<RequestResponseLog> logs = Arrays.asList(log1, log2);

        // Configure mock behavior
        when(requestResponseStatisticService.getRequestResponseList()).thenReturn(logs);

        // Execute and verify
        mockMvc.perform(get("/requestResponseStatistics/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("requestResponseMetricIndex"))
                .andExpect(model().attributeExists("requestResponseList"))
                .andExpect(model().attribute("requestResponseList", logs));
    }
} 