package com.schedulecore.ufu.infrasctructure.api.request;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleRequest {
    @NotNull
    private Integer monthDay;
    @NotNull
    private Integer month;
    private String campus;
}
