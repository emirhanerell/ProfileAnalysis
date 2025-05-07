package com.tesh.Dtos.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CpuUsageByServerAndMinute {
    private String server_name;
    private LocalDateTime check_minute;
    private Double avg_cpu_usage;
}
