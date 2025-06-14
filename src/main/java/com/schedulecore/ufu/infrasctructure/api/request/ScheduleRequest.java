package com.schedulecore.ufu.infrasctructure.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleRequest {
    private int monthDay;
    private int  month;
    private String Campus;
}
