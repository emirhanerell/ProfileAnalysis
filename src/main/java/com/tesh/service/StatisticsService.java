package com.tesh.service;

import com.tesh.Dtos.statistics.*;
import com.tesh.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatisticsService {

    private final StatisticsRepository _statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this._statisticsRepository = statisticsRepository;
    }

    public List<AverageResponseTimeByDate> getAverageResponseTimeByDate(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getAverageResponseTimeByDate(startTime, endTime,"minute");
    }

    public List<RequestStatusCountByDate> getRequestStatusCountByDate(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getRequestStatusCountByDate(startTime, endTime,"minute");
    }

    public List<CpuUsageByServerAndMinute> getCpuUsageByServerAndMinute(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getCpuUsageByServerAndMinute(startTime, endTime);
    }

    public List<DiskUsageByServerAndMinute> getDiskUsageByServerAndMinute(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getDiskUsageByServerAndMinute(startTime, endTime);
    }

    public List<EndpointCallCountByDate> getEndpointCallCountByDate(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getEndpointCallCountByDate(startTime, endTime);
    }

    public List<MemoryUsageByServerAndMinute> getMemoryUsageByServerAndMinute(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getMemoryUsageByServerAndMinute(startTime, endTime);
    }

    public List<NetworkUsageByServerAndMinute> getNetworkUsageByServerAndMinute(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getNetworkUsageByServerAndMinute(startTime, endTime);
    }

    public List<UserAgentRequestCounts> getUserAgentRequestCounts(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getUserAgentRequestCounts(startTime, endTime);
    }

    public List<DeviceTypeCountByDate> getDeviceTypeCountByDate(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getDeviceTypeCountByDate(startTime, endTime);
    }

    public List<BrowserCountByDate> getBrowserCountByDate(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getBrowserCountByDate(startTime, endTime);
    }

    public List<CountryCountByDate> getCountryCountByDate(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getCountryCountByDate(startTime, endTime);
    }

    public List<CityCountByDate> getCityCountByDate(LocalDateTime startTime, LocalDateTime endTime) {
        return _statisticsRepository.getCityCountByDate(startTime, endTime);
    }
}
