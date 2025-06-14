package com.schedulecore.ufu.infrasctructure.database;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.infrasctructure.database.repositorys.ReservaRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.MonthDay;

@Component
@RequiredArgsConstructor
public class DatabaseAdapter implements DatabasePort {
    private final ReservaRepostiory reservaRepostiory;
    @Override
    public ScheduleModel getSchedulesByCampusAndMonthAndDay(Month month, MonthDay monthDay, String campus) {
        reservaRepostiory.findAll();
        return ScheduleModel.builder().build();
    }

    @Override
    public ScheduleModel getSchedulesByMonthAndDay(Month month, MonthDay monthDay) {
        return ScheduleModel.builder()
                .build();
    }
}
