package com.tesh.controller;

import com.tesh.Dtos.statistics.AllStatistics;
import com.tesh.Dtos.statistics.MemoryUsageByServerAndMinute;
import com.tesh.Dtos.statistics.NetworkUsageByServerAndMinute;
import com.tesh.Dtos.statistics.StatisticRequestModel;
import com.tesh.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/statisticData")
public class StatisticDataController {
    private final StatisticsService _statisticsService;

    public StatisticDataController(StatisticsService statisticsService) {
        _statisticsService = statisticsService;
    }

    @PostMapping("/allStatistics")
    public ResponseEntity<AllStatistics> myTest(@RequestBody StatisticRequestModel statisticRequestModel) {
        var startDate = statisticRequestModel.getStartDate();
        var endDate = statisticRequestModel.getEndDate();

        AllStatistics response = new AllStatistics();

        response.setAverageResponseTimeByDateStatistics(_statisticsService.getAverageResponseTimeByDate(startDate, endDate));
        response.setRequestStatusCountByDateStatistics(_statisticsService.getRequestStatusCountByDate(startDate, endDate));
        response.setCpuUsageByServerAndMinuteStatistics(_statisticsService.getCpuUsageByServerAndMinute(startDate, endDate));
        response.setDiskUsageByServerAndMinuteStatistics(_statisticsService.getDiskUsageByServerAndMinute(startDate, endDate));
        response.setEndpointCallCountByDateStatistics(_statisticsService.getEndpointCallCountByDate(startDate, endDate));

        var memoryUsageByServerAndMinuteStatistics = _statisticsService.getMemoryUsageByServerAndMinute(startDate, endDate);
        List<MemoryUsageByServerAndMinute> topTenMemoryUsageByServerAndDateStatistics =
                memoryUsageByServerAndMinuteStatistics.stream()
                .sorted(Comparator.comparing(MemoryUsageByServerAndMinute::getAvg_memory_usage).reversed())
                .limit(10)
                .collect(Collectors.toList());

        response.setMemoryUsageByServerAndMinuteStatistics(topTenMemoryUsageByServerAndDateStatistics);

        var networkUsageByServerAndMinuteStatistics = _statisticsService.getNetworkUsageByServerAndMinute(startDate, endDate);
        List<NetworkUsageByServerAndMinute> topTenNetworkUsageByServerAndDateStatistics =
                networkUsageByServerAndMinuteStatistics.stream()
                .sorted(Comparator.comparing(NetworkUsageByServerAndMinute::getAvg_network_usage).reversed())
                .limit(10)
                .collect(Collectors.toList());

        response.setNetworkUsageByServerAndMinuteStatistics(topTenNetworkUsageByServerAndDateStatistics);
        response.setUserAgentRequestCountsStatistics(_statisticsService.getUserAgentRequestCounts(startDate, endDate));
        response.setBrowserCountByDateStatistics(_statisticsService.getBrowserCountByDate(startDate, endDate));
        response.setDeviceTypeCountByDateStatistics(_statisticsService.getDeviceTypeCountByDate(startDate, endDate));
        response.setCityCountByDateStatistics(_statisticsService.getCityCountByDate(startDate, endDate));
        response.setCountryCountByDateStatistics(_statisticsService.getCountryCountByDate(startDate, endDate));

        return ResponseEntity.ok(response);
    }


}
