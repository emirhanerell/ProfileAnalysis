package com.tesh.service;

import com.tesh.model.RequestResponseDetails;
import com.tesh.model.RequestResponseLog;
import com.tesh.repository.RequestResponseDetailsRepository;
import com.tesh.repository.RequestResponseLogRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RequestResponseStatisticService {

    private final RequestResponseLogRepository _requestResponseLogRepository;
    private final RequestResponseDetailsRepository _requestResponseDetailsRepository;


    public RequestResponseStatisticService(RequestResponseLogRepository requestResponseLogRepository, RequestResponseDetailsRepository requestResponseDetailsRepository) {
        this._requestResponseLogRepository = requestResponseLogRepository;
        this._requestResponseDetailsRepository = requestResponseDetailsRepository;
    }

    public int saveRequestResponseLog(RequestResponseLog requestResponseLog) {
        var insertedLog = _requestResponseLogRepository.save(requestResponseLog);
        return insertedLog.getId();
    }

    public void saveRequestResponseDetails(RequestResponseDetails requestResponseDetails) {
        _requestResponseDetailsRepository.save(requestResponseDetails);
    }

    public List<RequestResponseLog> getRequestResponseList() {

        var result = _requestResponseLogRepository.findAll();

        //var result = new ArrayList<RequestResponseLog>();
        //var log1 = new RequestResponseLog();
        //log1.setMethod("GET");
        //log1.setEndpoint("/api/v1/products");
        //log1.setDuration(1000.0);
        //log1.setRequestDate(LocalDateTime.now());
        //log1.setResponseDate(LocalDateTime.now());
        //log1.setStatus(200);
        //log1.setId(1);


        //var log2 = new RequestResponseLog();
        //log2.setMethod("POST");
        //log2.setEndpoint("/api/v1/createProduct");
        //log2.setDuration(2000.0);
        //log2.setRequestDate(LocalDateTime.now());
        //log2.setResponseDate(LocalDateTime.now());
        //log2.setStatus(201);
        //log2.setId(2);

        //result.add(log1);
        //result.add(log2);

        return result;
    }

    public RequestResponseDetails getDetailByRequestId(int id) {

        var result = _requestResponseDetailsRepository.findByRequestId(id).orElse(null);

        //var result = new RequestResponseDetails();
        //result.setId(1);
        //result.setRequestContent("{sample request json}");
        //result.setResponseContent("{sample response json}");

        return result;
    }
}
