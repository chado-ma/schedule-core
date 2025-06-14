package com.schedulecore.ufu.domains.ports;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.infrasctructure.api.v1.ScheduleController;

import java.time.Month;
import java.time.MonthDay;

public interface DatabasePort {
    ScheduleModel getSchedulesByCampusAndMonthAndDay(Month month, MonthDay monthDay, String campus);
    ScheduleModel getSchedulesByMonthAndDay(Month month, MonthDay monthDay);
}
