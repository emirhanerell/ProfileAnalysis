package com.tesh.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void testProductCreation() {
        Product product = new Product();
        assertNotNull(product);
    }

    @Test
    void testProductSettersAndGetters() {
        Product product = new Product();
        
        // Test setters and getters
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100.0);
        
        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(100.0, product.getPrice());
    }
} 