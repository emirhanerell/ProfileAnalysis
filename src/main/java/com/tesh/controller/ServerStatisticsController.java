package com.tesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tesh.service.ServerStatisticService;

@Controller
public class ServerStatisticsController {

    private final ServerStatisticService _serverStatisticService;

    public ServerStatisticsController(ServerStatisticService serverStatisticService) {
        this._serverStatisticService = serverStatisticService;
    }

    @GetMapping("/serverStatistics/index")
    public String getServerStatistics(Model model) {
        var serverDetailsList = _serverStatisticService.getAllServerDetails();
        model.addAttribute("serverDetailsList", serverDetailsList);
        return "serverMetricIndex";
    }
}
