package com.schedulecore.ufu.domains.usecases;

import com.schedulecore.ufu.domains.models.ScheduleModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.resourses.GetSchedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSchedulesImpl implements GetSchedules {
    private final DatabasePort databasePort;

    @Override
    public ScheduleModel get(MonthDay monthDay, Month month) {
        return ScheduleModel.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .monthDay(MonthDay.now())
                .time(List.of())
                .build();
    }
}
