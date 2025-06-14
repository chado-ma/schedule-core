package com.schedulecore.ufu.infrasctructure.database;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.MonthDay;

@Component
public class DatabaseAdapter implements DatabasePort {
    @Override
    public ScheduleModel getSchedulesByCampusAndMonthAndDay(Month month, MonthDay monthDay, String campus) {
        return ScheduleModel.builder().build();
    }

    @Override
    public ScheduleModel getSchedulesByMonthAndDay(Month month, MonthDay monthDay) {
        return ScheduleModel.builder()
                .build();
    }
}
