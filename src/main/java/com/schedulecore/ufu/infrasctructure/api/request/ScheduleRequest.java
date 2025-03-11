package com.schedulecore.ufu.infrasctructure.api.request;

import lombok.Builder;
import lombok.Data;

import java.time.Month;
import java.time.MonthDay;

@Data
@Builder
public class ScheduleRequest {
    @Builder.Default
    private MonthDay monthDay = MonthDay.now();
    private Month month = Month.MARCH;
}
