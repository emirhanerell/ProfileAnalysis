package com.tesh.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "request_response_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponseLog extends BaseModel {
    @Column(name = "request_date")
    private LocalDateTime requestDate;
    @Column(name = "response_date")
    private LocalDateTime responseDate;
    @Column(name = "status")
    private int status;
    @Column(name = "endpoint")
    private String endpoint;
    @Column(name = "method")
    private String method;
    @Column(name = "duration")
    private Double duration;
    @Column(name="controller")
    private String controller;
    @Column(name="action")
    private String action;
    @Column(name="ip")
    private String ip;
    @Column(name="user_agent")
    private String userAgent;
    @Column(name = "device_type")
    private String deviceType;
    @Column(name = "operating_system")
    private String operatingSystem;
    @Column(name = "browser")
    private String browser;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestResponseLog that = (RequestResponseLog) o;
        return Objects.equals(requestDate, that.requestDate) &&
               Objects.equals(responseDate, that.responseDate) &&
               Objects.equals(status, that.status) &&
               Objects.equals(endpoint, that.endpoint) &&
               Objects.equals(operatingSystem, that.operatingSystem) &&
               Objects.equals(method, that.method) &&
               Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate, responseDate, status, endpoint, operatingSystem, method, duration);
    }
}
