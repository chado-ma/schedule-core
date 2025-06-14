package com.schedulecore.ufu.infrasctructure.api.request;

import lombok.Builder;
import lombok.Data;

import java.time.Month;
import java.time.MonthDay;

@Data
@Builder
public class ScheduleRequest {
    private int monthDay;
    private int  month;
    private String Campus;
}
