package com.tesh.controller;

import com.tesh.Dtos.TestApiRequest;
import com.tesh.Dtos.TestApiResponse;
import com.tesh.Dtos.statistics.AllStatistics;
import com.tesh.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Random;

@Controller
@RequestMapping("/test")
public class TestController {

    private final StatisticsService _statisticsService;

    public TestController(StatisticsService statisticsService) {
        this._statisticsService = statisticsService;
    }

    @GetMapping("/test1")
    public ResponseEntity<String> test1() {
        int randomNum = getRandomNumber(1000,10000);
        try{
            Thread.sleep(randomNum);

        } catch (Exception e) {
            System.out.println("Thread kesintiye uğradı!");
        }

        return ResponseEntity.ok("test1 completed");
    }

    @GetMapping("/test2")
    public ResponseEntity<TestApiResponse> test2() {
        int randomNum = getRandomNumber(1000,10000);
        try{
            Thread.sleep(randomNum);

        } catch (Exception e) {
            System.out.println("Thread kesintiye uğradı!");
        }

        var response = new TestApiResponse();
        response.setResultCode(getRandomNumber(100,500));
        response.setResultMessage("test2 completed");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/test3")
    public ResponseEntity<TestApiResponse> test3(@RequestBody TestApiRequest testApiRequest) {
        int randomNum = getRandomNumber(1000,10000);
        try{
            Thread.sleep(randomNum);

        } catch (Exception e) {
            System.out.println("Thread kesintiye uğradı!");
        }

        var response = new TestApiResponse();
        response.setResultCode(getRandomNumber(100,500) + testApiRequest.getAge());
        response.setResultMessage("test2 completed");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/myTest")
    public ResponseEntity<AllStatistics> myTest(){
        var startDate = LocalDateTime.now().minusDays(200);
        var endDate = LocalDateTime.now();

        AllStatistics response = new AllStatistics();

        response.setAverageResponseTimeByDateStatistics(_statisticsService.getAverageResponseTimeByDate(startDate, endDate));
        response.setRequestStatusCountByDateStatistics(_statisticsService.getRequestStatusCountByDate(startDate, endDate));
        response.setCpuUsageByServerAndMinuteStatistics(_statisticsService.getCpuUsageByServerAndMinute(startDate, endDate));
        response.setDiskUsageByServerAndMinuteStatistics(_statisticsService.getDiskUsageByServerAndMinute(startDate, endDate));
        response.setEndpointCallCountByDateStatistics(_statisticsService.getEndpointCallCountByDate(startDate, endDate));
        response.setMemoryUsageByServerAndMinuteStatistics(_statisticsService.getMemoryUsageByServerAndMinute(startDate, endDate));
        response.setNetworkUsageByServerAndMinuteStatistics(_statisticsService.getNetworkUsageByServerAndMinute(startDate, endDate));
        response.setUserAgentRequestCountsStatistics(_statisticsService.getUserAgentRequestCounts(startDate, endDate));

        return ResponseEntity.ok(response);
    }

    private int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }


}
