package com.tesh.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class RequestResponseDetailsTest {

    @Test
    void testRequestResponseDetailsCreation() {
        RequestResponseDetails details = new RequestResponseDetails();
        assertNotNull(details);
    }

    @Test
    void testRequestResponseDetailsSettersAndGetters() {
        RequestResponseDetails details = new RequestResponseDetails();
        
        // Test setters and getters using existing methods
        details.setRequestContent("{\"key\": \"value\"}");
        details.setResponseContent("{\"status\": \"success\"}");
        details.setIp("192.168.1.1");
        details.setBrowser("Chrome");
        
        assertEquals("{\"key\": \"value\"}", details.getRequestContent());
        assertEquals("{\"status\": \"success\"}", details.getResponseContent());
        assertEquals("192.168.1.1", details.getIp());
        assertEquals("Chrome", details.getBrowser());
    }
} 