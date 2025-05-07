package com.tesh.Dtos.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllStatistics {
    private List<AverageResponseTimeByDate> averageResponseTimeByDateStatistics;
    private List<RequestStatusCountByDate> requestStatusCountByDateStatistics;
    private List<CpuUsageByServerAndMinute> cpuUsageByServerAndMinuteStatistics;
    private List<DiskUsageByServerAndMinute> diskUsageByServerAndMinuteStatistics;
    private List<EndpointCallCountByDate> endpointCallCountByDateStatistics;
    private List<MemoryUsageByServerAndMinute> memoryUsageByServerAndMinuteStatistics;
    private List<NetworkUsageByServerAndMinute> networkUsageByServerAndMinuteStatistics;
    private List<UserAgentRequestCounts> userAgentRequestCountsStatistics;
    private List<DeviceTypeCountByDate> deviceTypeCountByDateStatistics;
    private List<BrowserCountByDate> browserCountByDateStatistics;
    private List<CityCountByDate> cityCountByDateStatistics;
    private List<CountryCountByDate> countryCountByDateStatistics;
}
