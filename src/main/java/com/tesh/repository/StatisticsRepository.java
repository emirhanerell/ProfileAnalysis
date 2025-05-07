package com.tesh.repository;

import com.tesh.Dtos.statistics.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ua_parser.Device;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StatisticsRepository {

    private final EntityManager entityManager;

    public StatisticsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<AverageResponseTimeByDate> getAverageResponseTimeByDate(LocalDateTime startDate, LocalDateTime endDate, String groupByInterval) {
        String sql = "EXEC dbo.GetAverageResponseTimeByDate :startDate, :endDate, :groupByInterval";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("groupByInterval", groupByInterval);

        List<Object[]> results = query.getResultList();

        List<AverageResponseTimeByDate> responseList = new ArrayList<>();
        for (Object[] row : results) {
            String timeGroup = (String) row[0];
            Double avgResponseTimeMs = (Double) row[1];

            AverageResponseTimeByDate item = new AverageResponseTimeByDate(timeGroup, avgResponseTimeMs);
            responseList.add(item);
        }

        return responseList;
    }

    public List<RequestStatusCountByDate> getRequestStatusCountByDate(LocalDateTime startDate,LocalDateTime endDate, String groupByInterval) {
        String sql = "EXEC dbo.[GetRequestStatusCountsByDate] :startDate, :endDate, :groupByInterval";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("groupByInterval", groupByInterval);

        List<Object[]> results = query.getResultList();

        List<RequestStatusCountByDate> responseList = new ArrayList<>();
        for (Object[] row : results) {
            String timeGroup = (String) row[0];
            int successCount = (int) row[1];
            int failureCount = (int) row[2];

            RequestStatusCountByDate item = new RequestStatusCountByDate(timeGroup, successCount, failureCount);
            responseList.add(item);
        }

        return responseList;
    }

    public List<CpuUsageByServerAndMinute> getCpuUsageByServerAndMinute(LocalDateTime startDate,LocalDateTime endDate){
        String sql = "EXEC dbo.[GetCpuUsageByServerAndMinute] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();
        List<CpuUsageByServerAndMinute> cpuUsageList = new ArrayList<>();
        for (Object[] row : results) {
            String server_name = (String) row[0];
            LocalDateTime check_minute = ((Timestamp)row[1]).toLocalDateTime();
            Double avg_cpu_usage = (Double) row[2];

            CpuUsageByServerAndMinute item = new CpuUsageByServerAndMinute(server_name, check_minute, avg_cpu_usage);
            cpuUsageList.add(item);
        }
        return cpuUsageList;
    }

    public List<DiskUsageByServerAndMinute> getDiskUsageByServerAndMinute(LocalDateTime startDate,LocalDateTime endDate){
        String sql = "EXEC dbo.[GetDiskUsageByServerAndMinute] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();
        List<DiskUsageByServerAndMinute> diskUsageList = new ArrayList<>();
        for (Object[] row : results) {
            String server_name = (String) row[0];
            LocalDateTime check_minute = ((Timestamp) row[1]).toLocalDateTime();
            Double avg_disk_usage = (Double) row[2];

            DiskUsageByServerAndMinute item = new DiskUsageByServerAndMinute(server_name, check_minute, avg_disk_usage);
            diskUsageList.add(item);
        }
        return diskUsageList;
    }

    public List<EndpointCallCountByDate> getEndpointCallCountByDate(LocalDateTime startDate, LocalDateTime endDate) {
        String sql = "EXEC dbo.[GetEndpointCallCounts] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();

        List<EndpointCallCountByDate> responseList = new ArrayList<>();
        for (Object[] row : results) {
            String endpoint = (String) row[0];
            int callCount = (int) row[1];

            EndpointCallCountByDate item = new EndpointCallCountByDate(endpoint, callCount);
            responseList.add(item);
        }

        return responseList;
    }

    public List<MemoryUsageByServerAndMinute> getMemoryUsageByServerAndMinute(LocalDateTime startDate, LocalDateTime endDate){
        String sql = "EXEC dbo.[GetMemoryUsageByServerAndMinute] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();
        List<MemoryUsageByServerAndMinute> memoryUsageList = new ArrayList<>();
        for (Object[] row : results) {
            String server_name = (String) row[0];
            LocalDateTime check_minute = ((Timestamp) row[1]).toLocalDateTime();
            Double avg_memory_usage = (Double) row[2];

            MemoryUsageByServerAndMinute item = new MemoryUsageByServerAndMinute(server_name, check_minute, avg_memory_usage);
            memoryUsageList.add(item);
        }
        return memoryUsageList;
    }

    public List<NetworkUsageByServerAndMinute> getNetworkUsageByServerAndMinute(LocalDateTime startDate, LocalDateTime endDate){
        String sql = "EXEC dbo.[GetNetworkUsageByServerAndMinute] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();
        List<NetworkUsageByServerAndMinute> networkUsageList = new ArrayList<>();
        for (Object[] row : results) {
            String server_name = (String) row[0];
            LocalDateTime check_minute = ((Timestamp) row[1]).toLocalDateTime();
            Double avg_network_usage = (Double) row[2];

            NetworkUsageByServerAndMinute item = new NetworkUsageByServerAndMinute(server_name, check_minute, avg_network_usage);
            networkUsageList.add(item);
        }
        return networkUsageList;
    }

    public List<UserAgentRequestCounts> getUserAgentRequestCounts(LocalDateTime startDate, LocalDateTime endDate){
        String sql = "EXEC dbo.[GetUserAgentRequestCounts] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();

        List<UserAgentRequestCounts> responseList = new ArrayList<>();
        for (Object[] row : results) {
            String user_agent = (String) row[0];
            int request_count = (int) row[1];

            UserAgentRequestCounts item = new UserAgentRequestCounts(user_agent, request_count);
            responseList.add(item);
        }

        return responseList;
    }

    public List<BrowserCountByDate> getBrowserCountByDate(LocalDateTime startDate, LocalDateTime endDate){
        String sql = "EXEC dbo.[GetBrowserCountsByDate] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();

        List<BrowserCountByDate> responseList = new ArrayList<>();
        for (Object[] row : results) {
            String browser = (String) row[0];
            int browser_count = (int) row[1];

            BrowserCountByDate item = new BrowserCountByDate(browser, browser_count);
            responseList.add(item);
        }

        return responseList;
    }

    public List<DeviceTypeCountByDate> getDeviceTypeCountByDate(LocalDateTime startDate, LocalDateTime endDate){
        String sql = "EXEC dbo.[GetDeviceTypeCountsByDate] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();

        List<DeviceTypeCountByDate> responseList = new ArrayList<>();
        for (Object[] row : results) {
            String device_type = (String) row[0];
            int device_type_count = (int) row[1];

            DeviceTypeCountByDate item = new DeviceTypeCountByDate(device_type, device_type_count);
            responseList.add(item);
        }

        return responseList;
    }

    public List<CityCountByDate> getCityCountByDate(LocalDateTime startDate, LocalDateTime endDate){
        String sql = "EXEC dbo.[GetCityCountsByDateRange] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();

        List<CityCountByDate> responseList = new ArrayList<>();
        for (Object[] row : results) {
            String city = (String) row[0];
            int cityCount = (int) row[1];

            CityCountByDate item = new CityCountByDate(city, cityCount);
            responseList.add(item);
        }

        return responseList;
    }

    public List<CountryCountByDate> getCountryCountByDate(LocalDateTime startDate, LocalDateTime endDate){
        String sql = "EXEC dbo.[GetCountryCountsByDateRange] :startDate, :endDate";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        List<Object[]> results = query.getResultList();

        List<CountryCountByDate> responseList = new ArrayList<>();
        for (Object[] row : results) {
            String country = (String) row[0];
            int countryCount = (int) row[1];

            CountryCountByDate item = new CountryCountByDate(country, countryCount);
            responseList.add(item);
        }

        return responseList;
    }
}
