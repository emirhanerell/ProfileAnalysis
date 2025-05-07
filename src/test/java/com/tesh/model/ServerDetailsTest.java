package com.tesh.model;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class ServerDetailsTest {

    @Test
    void testServerDetailsCreation() {
        ServerDetails details = new ServerDetails();
        assertNotNull(details);
    }

    @Test
    void testServerDetailsSettersAndGetters() {
        ServerDetails details = new ServerDetails();
        
        // Test setters and getters using existing methods
        details.setCpuUsage(75.5);
        details.setMemoryUsage(80.0);
        details.setDiskUsage(60.0);
        details.setNetworkUsage(50.0);
        details.setServerName("TestServer");
        details.setServerIp("192.168.1.1");
        details.setCheckDate(LocalDateTime.now());
        
        assertEquals(75.5, details.getCpuUsage());
        assertEquals(80.0, details.getMemoryUsage());
        assertEquals(60.0, details.getDiskUsage());
        assertEquals(50.0, details.getNetworkUsage());
        assertEquals("TestServer", details.getServerName());
        assertEquals("192.168.1.1", details.getServerIp());
        assertNotNull(details.getCheckDate());
    }
} 