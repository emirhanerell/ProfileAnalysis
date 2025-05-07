package com.tesh.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.tesh.model.RequestResponseLog;
import com.tesh.repository.RequestResponseLogRepository;

class RequestResponseStatisticServiceTest {

    @Mock
    private RequestResponseLogRepository requestResponseLogRepository;

    @InjectMocks
    private RequestResponseStatisticService requestResponseStatisticService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRequestResponseLogs() {
        // Prepare test data
        RequestResponseLog log1 = new RequestResponseLog();
        log1.setMethod("GET");
        log1.setEndpoint("/api/v1/products");
        log1.setDuration(1000.0);
        log1.setStatus("200");
        log1.setOperatingSystem("Windows");

        RequestResponseLog log2 = new RequestResponseLog();
        log2.setMethod("POST");
        log2.setEndpoint("/api/v1/createProduct");
        log2.setDuration(2000.0);
        log2.setStatus("201");
        log2.setOperatingSystem("Linux");

        List<RequestResponseLog> expectedLogs = Arrays.asList(log1, log2);

        // Configure mock behavior
        when(requestResponseLogRepository.findAll()).thenReturn(expectedLogs);

        // Execute the method
        List<RequestResponseLog> actualLogs = requestResponseStatisticService.getRequestResponseList();

        // Verify
        assertNotNull(actualLogs);
        assertEquals(expectedLogs.size(), actualLogs.size());
        for (int i = 0; i < expectedLogs.size(); i++) {
            RequestResponseLog expected = expectedLogs.get(i);
            RequestResponseLog actual = actualLogs.get(i);
            assertEquals(expected.getMethod(), actual.getMethod());
            assertEquals(expected.getEndpoint(), actual.getEndpoint());
            assertEquals(expected.getDuration(), actual.getDuration());
            assertEquals(expected.getStatus(), actual.getStatus());
            assertEquals(expected.getOperatingSystem(), actual.getOperatingSystem());
        }
        verify(requestResponseLogRepository).findAll();
    }
} 