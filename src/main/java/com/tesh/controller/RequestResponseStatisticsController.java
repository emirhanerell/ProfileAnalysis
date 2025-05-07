package com.tesh.controller;

import com.tesh.service.RequestResponseStatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tesh.service.RequestResponseStatisticService;

@Controller
public class RequestResponseStatisticsController {

    private final RequestResponseStatisticService _requestResponseStatisticService;

    public RequestResponseStatisticsController(RequestResponseStatisticService requestResponseStatisticService) {
        this._requestResponseStatisticService = requestResponseStatisticService;
    }

    @GetMapping("/requestResponseStatistics/index")
    public String dashboard(Model model) {
        var requestResponseList = _requestResponseStatisticService.getRequestResponseList();
        model.addAttribute("requestResponseList", requestResponseList);
        return "requestResponseMetricIndex";
    }

    @GetMapping("/requestResponseStatistics/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        var details = _requestResponseStatisticService.getDetailByRequestId(id);
        model.addAttribute("details", details);
        return "requestResponseMetricDetail";
    }
}
