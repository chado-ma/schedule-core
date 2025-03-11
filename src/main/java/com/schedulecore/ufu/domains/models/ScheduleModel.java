package com.schedulecore.ufu.domains.models;


import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.List;

@Data
@Builder
public class ScheduleModel {
    private DayOfWeek dayOfWeek;
    private MonthDay monthDay;
    private List<Time> time;

}
