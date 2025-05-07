package com.tesh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tesh.model.ServerDetails;
import com.tesh.repository.ServerDetailsRepository;
import com.tesh.utilities.ServerInformationHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServerStatisticService {

    private final ServerDetailsRepository _serverDetailsRepository;

    public ServerStatisticService(ServerDetailsRepository serverDetailsRepository) {
        this._serverDetailsRepository = serverDetailsRepository;
    }

    public void saveServerDetails(ServerDetails serverDetails) {
        _serverDetailsRepository.save(serverDetails);
    }

    @Scheduled(fixedRate = 10000)
    public void runTask() throws InterruptedException {
        var serverDetails = new ServerDetails();
        serverDetails.setCheckDate(LocalDateTime.now());
        serverDetails.setServerName(ServerInformationHelper.getServername());
        serverDetails.setCpuUsage(ServerInformationHelper.getCpuUsage());
        serverDetails.setServerIp(ServerInformationHelper.getIpAddress());
        serverDetails.setDiskUsage(ServerInformationHelper.getDiskUsage());
        serverDetails.setMemoryUsage(ServerInformationHelper.getMemoryUsage());
        serverDetails.setNetworkUsage(ServerInformationHelper.getNetworkUsage());
        _serverDetailsRepository.save(serverDetails);
    }

    public List<ServerDetails> getServerDetailsList() {
        var result = _serverDetailsRepository.findAll();
        return result;
    }
    public List<ServerDetails> getAllServerDetails() {
        return _serverDetailsRepository.findAll();
    }

}
