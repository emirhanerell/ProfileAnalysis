package com.tesh.service;

import java.time.LocalDateTime;
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

import com.tesh.model.ServerDetails;
import com.tesh.repository.ServerDetailsRepository;

class ServerStatisticServiceTest {

    @Mock
    private ServerDetailsRepository serverDetailsRepository;

    @InjectMocks
    private ServerStatisticService serverStatisticService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllServerDetails() {
        // Arrange
        ServerDetails server1 = new ServerDetails();
        server1.setCheckDate(LocalDateTime.now());
        server1.setServerName("Server1");
        server1.setCpuUsage(10.0);
        server1.setServerIp("123.23.23.11");
        server1.setMemoryUsage(20.0);
        server1.setDiskUsage(30.0);
        server1.setNetworkUsage(40.0);
        server1.setId(1);

        ServerDetails server2 = new ServerDetails();
        server2.setCheckDate(LocalDateTime.now());
        server2.setServerName("Server2");
        server2.setCpuUsage(15.0);
        server2.setServerIp("123.23.23.12");
        server2.setMemoryUsage(25.0);
        server2.setDiskUsage(35.0);
        server2.setNetworkUsage(45.0);
        server2.setId(2);

        List<ServerDetails> expectedServers = Arrays.asList(server1, server2);
        when(serverDetailsRepository.findAll()).thenReturn(expectedServers);

        // Act
        List<ServerDetails> actualServers = serverStatisticService.getAllServerDetails();

        // Assert
        assertNotNull(actualServers);
        assertEquals(2, actualServers.size());
        assertEquals(expectedServers, actualServers);
        verify(serverDetailsRepository).findAll();
    }

    @Test
    void testGetDetailByServerId() {
        // Arrange
        ServerDetails expectedServer = new ServerDetails();
        expectedServer.setCheckDate(LocalDateTime.now());
        expectedServer.setServerName("Server1");
        expectedServer.setCpuUsage(10.0);
        expectedServer.setServerIp("123.23.23.11");
        expectedServer.setMemoryUsage(20.0);
        expectedServer.setDiskUsage(30.0);
        expectedServer.setNetworkUsage(40.0);
        expectedServer.setId(1);

        when(serverDetailsRepository.findByServerId(1)).thenReturn(expectedServer);

        // Act
        ServerDetails actualServer = serverStatisticService.getDetailByServerId(1);

        // Assert
        assertNotNull(actualServer);
        assertEquals(expectedServer, actualServer);
        verify(serverDetailsRepository).findByServerId(1);
    }
} 