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
public class MemoryUsageByServerAndMinute {
    private String server_name;
    private LocalDateTime check_minute;
    private double avg_memory_usage;
}
