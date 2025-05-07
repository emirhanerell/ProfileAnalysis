package com.tesh.beans;

import com.tesh.model.RequestResponseDetails;
import com.tesh.model.RequestResponseLog;
import com.tesh.service.RequestResponseStatisticService;
import com.tesh.utilities.IPLocationFinder;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ua_parser.Client;
import ua_parser.Parser;

@Component
public class RequestResponseLoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(RequestResponseLoggingInterceptor.class);
    private final RequestResponseStatisticService _requestResponseStatisticService;

    @Autowired
    public RequestResponseLoggingInterceptor(RequestResponseStatisticService requestResponseStatisticService) {
        this._requestResponseStatisticService = requestResponseStatisticService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        // Response'u cache edebilmek için ContentCachingResponseWrapper ile sar
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String ip = request.getHeader("X-Forwarded-For");
        //ip = "212.253.61.101"; // istanbul
        //ip = "88.226.75.42"; // ankara

        if (ip == null)
            ip = request.getRemoteAddr();

        String country = ip.contains("0:0") || ip.contains("127.0.")  ? "-" : IPLocationFinder.getLocation(ip)[0];
        String city = ip.contains("0:0") || ip.contains("127.0.") ? "-" : IPLocationFinder.getLocation(ip)[1];

        String userAgent = request.getHeader("User-Agent");

        String controllerName = "UnknownController";
        String actionName = "UnknownMethod";

        // HandlerMethod ile controller ve action bilgilerine erişim
        if (handler instanceof HandlerMethod handlerMethod) {
            controllerName = handlerMethod.getBeanType().getSimpleName(); // Controller Adı
            actionName = handlerMethod.getMethod().getName(); // Method Adı
        }
        long startTime = (Long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        Parser uaParser = new Parser();
        Client client = uaParser.parse(userAgent);

        String deviceFamily = client.device.family;  // "iPhone", "Samsung", "Other"
        String osFamily = client.os.family;          // "iOS", "Android", "Windows"
        String userAgentFamily = client.userAgent.family; // "Mobile Safari", "Chrome"

        String deviceType = "PC";

        if (!"Other".equalsIgnoreCase(deviceFamily))
           deviceType = "Mobile";

        if (controllerName.toLowerCase().equals("testcontroller"))
        {
            var log = createLog(startTime,System.currentTimeMillis(),controllerName, actionName, ip, userAgent,
                    request.getMethod(), request.getRequestURI(), duration, response.getStatus(),deviceType,osFamily,
                    userAgentFamily,city,country);
            var logId = _requestResponseStatisticService.saveRequestResponseLog(log);

            var requestBody = getRequestContent(request);
            var responseBody = getResponseContent(response);

            var requestResponseDetails = createDetail(logId,requestBody,responseBody);
            _requestResponseStatisticService.saveRequestResponseDetails(requestResponseDetails);

            String logMessage = MessageFormat.format("controller {0} action {1} ip {2} user-agent {3} method {4} uri {5} duration {6} ms responseStatus {7}, requestbody {8}, responsebody {9}",
                    controllerName, actionName, ip, userAgent, request.getMethod(), request.getRequestURI(),duration,response.getStatus(),requestBody,responseBody);

            logger.info(logMessage);
        }
    }

    private RequestResponseLog createLog(long startDate,long endDate,String controllerName, String actionName,
                                         String ip, String userAgent, String method, String uri, long duration,
                                         int responseStatus,String deviceType,String operatingSystem,String browser,String city,String country) {
        var log = new RequestResponseLog();
        log.setController(controllerName);
        log.setAction(actionName);
        log.setIp(ip);
        log.setUserAgent(userAgent);
        log.setMethod(method);
        log.setEndpoint(uri);
        log.setDuration((double)duration);
        log.setStatus(responseStatus);
        log.setRequestDate(millisecondToDatetime(startDate));
        log.setResponseDate(millisecondToDatetime(endDate));
        log.setDeviceType(deviceType);
        log.setOperatingSystem(operatingSystem);
        log.setBrowser(browser);
        log.setCity(city);
        log.setCountry(country);
        return log;

    }

    private LocalDateTime millisecondToDatetime(long millisecond) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(millisecond)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return localDateTime;
    }

    private RequestResponseDetails createDetail(int requestId,String requestContent,String responseContent){
        var detail = new RequestResponseDetails();
        detail.setRequestId(requestId);
        detail.setRequestContent(requestContent);
        detail.setResponseContent(responseContent);
        return detail;
    }


    private String getRequestContent(HttpServletRequest request) throws UnsupportedEncodingException {
        if (!(request instanceof ContentCachingRequestWrapper wrappedRequest)) {
            return "Request body cannot be captured!";
        }

        byte[] content = wrappedRequest.getContentAsByteArray();
        return content.length > 0 ? new String(content, request.getCharacterEncoding()) : "";
    }

    private String getResponseContent(HttpServletResponse response) throws UnsupportedEncodingException {
        if (!(response instanceof ContentCachingResponseWrapper wrappedResponse)) {
            return "Response body cannot be captured!";
        }

        byte[] content = wrappedResponse.getContentAsByteArray();
        return content.length > 0 ? new String(content, response.getCharacterEncoding()) : "";
    }
}