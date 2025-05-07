package com.tesh.model;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class RequestResponseLogTest {

    @Test
    void testRequestResponseLogCreation() {
        RequestResponseLog log = new RequestResponseLog();
        assertNotNull(log);
    }

    @Test
    void testRequestResponseLogSettersAndGetters() {
        RequestResponseLog log = new RequestResponseLog();
        
        // Test setters and getters using existing methods
        LocalDateTime now = LocalDateTime.now();
        log.setRequestDate(now);
        log.setResponseDate(now.plusSeconds(1));
        log.setStatus("SUCCESS");
        log.setEndpoint("/api/test");
        log.setOperatingSystem("Windows");
        log.setMethod("GET");
        log.setDuration(1.0);
        
        assertEquals(now, log.getRequestDate());
        assertEquals(now.plusSeconds(1), log.getResponseDate());
        assertEquals("SUCCESS", log.getStatus());
        assertEquals("/api/test", log.getEndpoint());
        assertEquals("Windows", log.getOperatingSystem());
        assertEquals("GET", log.getMethod());
        assertEquals(1.0, log.getDuration());
    }
} 