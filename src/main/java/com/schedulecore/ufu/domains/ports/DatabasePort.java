package com.schedulecore.ufu.domains.ports;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.infrasctructure.api.v1.ScheduleController;

import java.time.Month;
import java.time.MonthDay;
import java.util.List;

public interface DatabasePort {
    List<ScheduleModel> getSchedulesByCampusAndMonthAndDay(Month month, MonthDay monthDay, String campus);
    List<ScheduleModel> getSchedulesByMonthAndDay(Month month, MonthDay monthDay);
}
