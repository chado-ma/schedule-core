package com.schedulecore.ufu.domains.chainresponsibility.newschedule;

import com.schedulecore.ufu.domains.chainresponsibility.ScheduleValidator;
import com.schedulecore.ufu.domains.models.NewSchedule;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleDuplicated extends ScheduleValidator {
    private final DatabasePort databasePort;

    @Override
    public boolean check(NewSchedule schedule) {
        databasePort.findScheduleByHorarioAndMounthDayAndGinasio(
                schedule.getHorario(),
                schedule.getData(),
                schedule.getGinasio()
        ).ifPresent(model -> {
            throw new IllegalArgumentException("Schedule already exists for the given time and date: " + model);
        });
        return checkNext(schedule);
    }
}
