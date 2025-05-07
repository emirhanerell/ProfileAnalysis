package com.tesh.Dtos.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AverageResponseTimeByDate {

    private String timeGroup;
    private Double avgResponseTimeMs;
}